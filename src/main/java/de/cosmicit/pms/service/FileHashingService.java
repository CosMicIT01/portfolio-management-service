package de.cosmicit.pms.service;

import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service("fileHashingService")
public class FileHashingService {

    /**
     * @param file
     * @return fileHash the file hash based on the content
     */

    public String getFileHash(File file) {
        MessageDigest md = null;
        String fileHash = null;
        FileInputStream fileInputStream = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(Files.readAllBytes(Paths.get(file.getPath())));
            byte[] digest = md.digest();
            String digestInHex = DatatypeConverter.printHexBinary(digest).toLowerCase();
            fileHash = digestInHex.substring(0, 8);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileHash;
    }

    public String get8BitHash(byte[] contents) {
        String fileHash = null;
        try {
            String completeHash = getCompleteHash(contents);
            fileHash = completeHash.substring(0, 8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileHash;
    }

    public String getCompleteHash(byte[] contents) {
        String completeHash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(contents);
            byte[] digest = md.digest();
            completeHash = DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return completeHash;
    }

    public String getEncodedClientId(String clientKey, String clientSecret) throws UnsupportedEncodingException {
        String encodedClientId;
        String clientId = clientKey + ":" + clientSecret;
        encodedClientId = Base64.getEncoder().encodeToString(clientId.getBytes("utf-8"));
        return encodedClientId;
    }
}