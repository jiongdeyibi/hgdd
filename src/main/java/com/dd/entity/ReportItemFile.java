package com.dd.entity;

import java.util.UUID;

public class ReportItemFile {
    private String attId;

    private String fileType;

    private String filePath;

    private String reportItemId;

    public ReportItemFile(){
        this.attId= UUID.randomUUID().toString().replaceAll("-", "");
    }
    /**
     * @param fileType,filePath,reportItemId
     *
     */
    public ReportItemFile(String attId,String fileType, String filePath, String reportItemId) {
        this.attId= attId;
        this.fileType = fileType;
        this.filePath = filePath;
        this.reportItemId = reportItemId;
    }

    public String getAttId() {
        return attId;
    }

    public void setAttId(String attId) {
        this.attId = attId == null ? null : attId.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getReportItemId() {
        return reportItemId;
    }

    public void setReportItemId(String reportItemId) {
        this.reportItemId = reportItemId == null ? null : reportItemId.trim();
    }

    @Override
    public String toString() {
        return "ReportItemFile{" +
                "attId='" + attId + '\'' +
                ", fileType='" + fileType + '\'' +
                ", filePath='" + filePath + '\'' +
                ", reportItemId='" + reportItemId + '\'' +
                '}';
    }
}