package de.cosmicit.pms.common;


public class SuccessResponse {


    private long timestamp;
    private int status;
    private String success;
    private String filename;
    private String path;

    private SuccessResponse(int status, String error, String filename, String path) {
        this.status = status;
        this.success = error;
        this.filename = filename;
        this.path = path;
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    public static SuccessResponse create(int status, String success, String filename, String path) {
        return new SuccessResponse(status, success, filename, path);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}