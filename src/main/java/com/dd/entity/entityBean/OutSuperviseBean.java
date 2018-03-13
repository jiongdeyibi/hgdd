package com.dd.entity.entityBean;

import com.dd.entity.OutSupervise;

public class OutSuperviseBean extends OutSupervise{
    private String fileId;
    private String fileName;
    private String uploadTime;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public String toString() {
        return "OutSuperviseBean{" +
                "fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                '}';
    }
}
