package com.dd.entity;

import java.util.UUID;

public class ReportItem {
    private String reportItemId;

    private String reportId;

    private String dictReportItemId;

    private String status;

    private String description;

    public String getReportItemId() {
        return reportItemId;
    }

    public ReportItem(){
        this.reportItemId = UUID.randomUUID().toString().replaceAll("-", "");
        this.status = "N";
    }

    public void setReportItemId(String reportItemId) {
        this.reportItemId = reportItemId == null ? null : reportItemId.trim();
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId == null ? null : reportId.trim();
    }

    public String getDictReportItemId() {
        return dictReportItemId;
    }

    public void setDictReportItemId(String dictReportItemId) {
        this.dictReportItemId = dictReportItemId == null ? null : dictReportItemId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        return "ReportItem{" +
                "reportItemId='" + reportItemId + '\'' +
                ", reportId='" + reportId + '\'' +
                ", dictReportItemId='" + dictReportItemId + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}