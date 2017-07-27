package de.cosmicit.pms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service("fileDirectoryUtil")
public class FileDirectoryUtil {

    private static final String BASE_DIR = "/static";

    private static final String IDENTITY_INPUT_DIR = "/identity";
    private static final String CONTRACT_INPUT_DIR = "/contract";
    private static final String CSS_INPUT_DIR = "/css";
    private static final String SCSS_INPUT_DIR = "/scss";
    private static final String JAVASCRIPT_INPUT_DIR = "/js";

    private static final String ORGANIZER_DIR = "/organizer";
    private static final String GAME_DIR = "/game";
    private static final String TOURNAMENT_DIR = "/tournament";
    private static final String CLUB_DIR = "/club";
    private static final String PRIZE_DIR = "/prize";

    private static final String USER_PROFILE_DIR = "/profile";

    private final ValidationService validatorService = new ValidationService();

    @Value("${tmp.dir.path}")
    private String tempDirPath;

    @Value("${static.assets.dir.path}")
    private String staticAssetsDirPath;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String getSourceDirectoryPathByDocumentType(int documentType) {

        String srcDirPath = null;

        switch (documentType) {
            case 1:
                srcDirPath = IDENTITY_INPUT_DIR;
                break;
            case 2:
                srcDirPath = CONTRACT_INPUT_DIR;
                break;
            default:
                break;
        }
        return srcDirPath;
    }


//    public String getSourceDirectoryPathByEntityType(EntityType assetEntityType) {
//
//        String srcDirPath = "";
//
//        switch (assetEntityType) {
//            case ORGANIZER:
//                srcDirPath = ORGANIZER_DIR;
//                break;
//            case GAME:
//                srcDirPath = GAME_DIR;
//                break;
//            case TOURNAMENT:
//                srcDirPath = TOURNAMENT_DIR;
//                break;
//            case CLUB:
//                srcDirPath = CLUB_DIR;
//                break;
//            case PRIZE:
//                srcDirPath = PRIZE_DIR;
//                break;
//            default:
//                break;
//        }
//        return srcDirPath;
//    }

    public String getBaseDirectoryForStaticAssets() {
        return BASE_DIR;
    }

    public String getBaseDirectoryForUserProfile() {
        return USER_PROFILE_DIR;
    }

    public String getOriginalFileExtension(File file) {
        String originalFileExtension = "";
        Path source = Paths.get(file.getPath());
        try {
            originalFileExtension = Files.probeContentType(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return originalFileExtension;
    }

    public String slashify(String path, boolean isDirectory) {
        String p = path;
        if (File.separatorChar != '/')
            p = p.replace(File.separatorChar, '/');
        if (!p.startsWith("/"))
            p = "/" + p;
        if (!p.endsWith("/") && isDirectory)
            p = p + "/";
        return p;
    }

    public String createTempFile(MultipartFile multipartFile) {
        File inputFile = null;
        String inputFileName = null;
        StringBuffer tempInputDirectoryPath = new StringBuffer(tempDirPath)
                .append(File.separator)
                .append("input");
        FileOutputStream fos = null;
        try {
            File directory = new File(tempInputDirectoryPath.toString());
            if (!directory.exists()) {
                directory.mkdirs();
            }
            inputFileName = tempInputDirectoryPath.append(File.separator).append(multipartFile.getOriginalFilename()).toString();
            inputFile = new File(inputFileName);
            if (!inputFile.exists()) {
                inputFile.createNewFile();
            }
            fos = new FileOutputStream(inputFile);
            fos.write(multipartFile.getBytes());
            fos.close();
        } catch (final IOException e) {
            logger.error("An exception occurred while creating temporary file  : {} ", e.getMessage());
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return inputFileName;
    }

    public void deleteTempDirectory() {
        try {
            File tempDirectory = new File(tempDirPath);
            deleteDirectory(tempDirectory);
        } catch (Exception e) {
            logger.error("An exception occurred while creating deleting temporary directory  : {} ", e.getMessage());
            e.printStackTrace();
        }

    }

    private boolean deleteDirectory(File dir) {

        if (!dir.exists() || !dir.isDirectory()) {
            return false;
        }

        String[] files = dir.list();
        for (int i = 0, len = files.length; i < len; i++) {
            File f = new File(dir, files[i]);
            if (f.isDirectory()) {
                deleteDirectory(f);
            } else {
                f.delete();
            }
        }
        return dir.delete();
    }

    public boolean isValidDirectory(String directoryPath) {
        boolean isValid = true;
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            isValid = false;
        }
        return isValid;
    }

    public String getCDNRelativePath(String originalFilePath) throws FileNotFoundException {
        String standardFilePath = slashify(originalFilePath, false);
        String relativePath = originalFilePath.substring(standardFilePath.indexOf(BASE_DIR));
        return slashify(relativePath, false);
    }



//    public String getCDNRelativePath(String originalFilePath, AssetType assetType) throws FileNotFoundException {
//        String standardFilePath = slashify(originalFilePath, false);
//        String relativePath = originalFilePath.substring(standardFilePath.indexOf(getSourceDirectoryPathByType(assetType)));
//        return slashify(relativePath, false);
//    }


}