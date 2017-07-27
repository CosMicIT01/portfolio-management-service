package de.cosmicit.pms.service;


import de.cosmicit.pms.common.ValidationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service("validationService")
public class ValidationService {

    private static final String[] ALLOWED_IMAGE_EXTENSIONS = {"jpg", "jpeg", "png", "gif", "svg+xml"};
    public static final String PERMISSION_DENIED_FOR_UPLOAD = "the selected role does not have permission for upload";
    public static final String FIELD_NAME_INVALID_ROLE = "invalid role";
    public static final String FIELD_NAME_FILE_SIZE = "file size";
    public static final String SELECT_FILE = "Please select a file to upload";
    public static final String FILE_SIZE_EXCEEDS_2_MB = "file size exceeds the maximum specified limit. \n Please select file smaller than 2MB";
    public static final String FIELD_NAME_FILE_EXTENSION = "file extension";
    public static final String UPLOAD_PROFILE_PERMISSION_ONLY = "the selected role can only upload profile";
    public static final long FILE_SIZE_2MB = 2 * (1024L * 1024L);

//    @Value("${ess-asset-service.av-server-mode}")
//    private String avMode;
//
//    @Value("${ess-asset-service.av-server-host}")
//    private String avServerHost;
//
//    @Value("${ess-asset-service.av-server-port}")
//    private String avServerPort;

    public ValidationResponse validateImageFile(MultipartFile inputFile) {
        ValidationResponse validationResponse = new ValidationResponse();
        boolean isValid = true;
        if (inputFile.isEmpty()) {
            validationResponse.setFieldName(FIELD_NAME_FILE_SIZE);
            validationResponse.setMessage(SELECT_FILE);
            isValid = false;
        }
        if ((inputFile.getSize() / FILE_SIZE_2MB) > 1) {
            validationResponse.setFieldName(FIELD_NAME_FILE_SIZE);
            validationResponse.setMessage(FILE_SIZE_EXCEEDS_2_MB);
            isValid = false;
        }
        String fileExtension = inputFile.getContentType().substring(inputFile.getContentType().lastIndexOf("/") + 1);
        List<String> allowedExtensionList = Arrays.asList(getAllowedExtensions());
        if (!allowedExtensionList.contains(fileExtension)) {
            validationResponse.setFieldName(FIELD_NAME_FILE_EXTENSION);
            validationResponse.setMessage("Invalid file extension.Please upload " + Arrays.asList(getAllowedExtensions()) + " files only ");
            isValid = false;
        }
        validationResponse.setValid(isValid);
        return validationResponse;

    }

    private String[] getAllowedExtensions() {
        return ALLOWED_IMAGE_EXTENSIONS;
    }

    // TODO scan file for Viruses
/*    public String scanFile(MultipartFile multipartFile) throws IOException, Exception  {
//        String scanMode = avMode != null ? avMode : "NOSCAN";
        byte[] fileBytes = multipartFile.getBytes();
        boolean scan = avMode.startsWith("SCAN");
        if (scan) {
//            AVClient avc = new AVClient(avServerHost, avServerPort, avMode);
            if (avc.scanfile(multipartFile.getOriginalFilename(),fileBytes ) == -1) {
                throw new Exception("WARNING: A virus was detected in your attachment: " + multipartFile.getOriginalFilename() + "<br>Please scan your system with the latest antivirus software with updated virus definitions and try again.");
            }
        }
    }*/
}