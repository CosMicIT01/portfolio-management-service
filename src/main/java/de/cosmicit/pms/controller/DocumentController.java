package de.cosmicit.pms.controller;

import de.cosmicit.pms.common.SuccessResponse;
import de.cosmicit.pms.common.ValidationResponse;
import de.cosmicit.pms.controller.exception.InvalidParameterException;
import de.cosmicit.pms.controller.exception.LogicalException;
import de.cosmicit.pms.controller.exception.ResourceNotFoundException;
import de.cosmicit.pms.model.entities.Document;
import de.cosmicit.pms.model.repository.DocumentRepository;
import de.cosmicit.pms.service.FileDirectoryUtil;
import de.cosmicit.pms.service.FileHashingService;
import de.cosmicit.pms.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(path = "/document")
public class DocumentController extends AbstractRestController<Document> {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    ValidationService validationService;

    @Autowired
    FileDirectoryUtil  fileDirectoryUtil;

    @Autowired
    FileHashingService fileHashingService;

    @Value("${static.assets.dir.path}")
    private String staticAssetsDirPath;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Class<Document> getEntityClass() {
        return Document.class;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Document get(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.get(id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Document> getList() {
        List<Document> documents = super.getList();
        return documents;
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Document create(@RequestBody Document entity) throws InvalidParameterException {
            super.create(entity);
        return entity;
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Document replace(@PathVariable("id") Long id, @RequestBody String jsonData)
            throws ResourceNotFoundException, InvalidParameterException, IOException {
        return super.replace(id, jsonData);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public Document update(@PathVariable("id") Long id, @RequestBody String jsonData)
            throws ResourceNotFoundException, InvalidParameterException, IOException {
        return super.update(id, jsonData);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        super.delete(id);
    }

    @RequestMapping(value = "/upload/{documenttype}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> singleFileUpload(HttpServletRequest request, @RequestParam("uploadfile") MultipartFile uploadfile, @PathVariable("documenttype") String documentType) throws LogicalException, FileNotFoundException {

        // check if the file is valid
        ValidationResponse validationResponse = validationService.validateImageFile(uploadfile);
        if (!validationResponse.isValid()) {
            throw new LogicalException(validationResponse.getMessage());
        }

        String directoryPath = "";
        String filePath = "";
        try {
            // Get the filename and build the local file path (be sure that the
            // application have write permissions on such directory)
            String filename = uploadfile.getOriginalFilename();
            String compressedFilePath = "";
            byte[] fileContents = uploadfile.getBytes();
            String fileHash = fileHashingService.get8BitHash(fileContents);
            logger.debug("File hash is : {}", fileHash);
            String hashedFilename = fileHash + "." + filename;

            directoryPath = getDirectoryPathByDocumentType(documentType);

            filePath = directoryPath + File.separator + hashedFilename;
            logger.debug("file path is {}", filePath);
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(fileContents);
            stream.close();
        } catch (Exception e) {
            throw new LogicalException(e.getMessage(), e);
        }

        fileDirectoryUtil.deleteTempDirectory();

        String cdnRelativePath = fileDirectoryUtil.getCDNRelativePath(filePath);
        return getSuccessResponseEntity(cdnRelativePath, request, HttpStatus.OK);
    }

    private String getDirectoryPathByDocumentType(String documentType) {
        String filePath;
        StringBuffer directoryPathBuffer = new StringBuffer("");
        directoryPathBuffer.append(staticAssetsDirPath)
                .append(fileDirectoryUtil.getBaseDirectoryForStaticAssets())
                .append(fileDirectoryUtil.getSourceDirectoryPathByDocumentType(Integer.parseInt(documentType)));

        File directory = new File(directoryPathBuffer.toString());
        if (!directory.exists())
            directory.mkdirs();
        filePath = Paths.get(directoryPathBuffer.toString()).toString();
        return filePath;
    }

    protected ResponseEntity<Object> getSuccessResponseEntity(String successMessage, HttpServletRequest request, HttpStatus status) {
        SuccessResponse successResponse = SuccessResponse.create(status.value(), status.getReasonPhrase(), successMessage, request.getRequestURI());
        return new ResponseEntity<>(successResponse, status);
    }
}
