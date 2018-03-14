package com.dd.entity;

import com.base.BaseModel;

public class UploadFile extends BaseModel {
    private String id;

    private String tableId;

    private String fileName;

    private String downloadPath;

    private String uploadTime;

    private String uploadUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId == null ? null : tableId.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath == null ? null : downloadPath.trim();
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime == null ? null : uploadTime.trim();
    }

    public String getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser == null ? null : uploadUser.trim();
    }

    @Override
    public String toString() {
        return "UploadFile{" +
                "id='" + id + '\'' +
                ", tableId='" + tableId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", downloadPath='" + downloadPath + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                ", uploadUser='" + uploadUser + '\'' +
                '}';
    }
}