package com.dd.entity;

import com.base.BaseModel;

public class OutSupervise extends BaseModel {
    private String id;

    private String busiType;

    private String datetime;

    private String checkName;

    private String checkInstitute;

    private String checkObject;

    private String problemInfo;

    private String superviseMeasure;

    private String rectifyClaim;

    private String rectifyStaff;

    private String rectifyTerm;

    private String rectifyStatus;

    private String rectifyConclusion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime == null ? null : datetime.trim();
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName == null ? null : checkName.trim();
    }

    public String getCheckInstitute() {
        return checkInstitute;
    }

    public void setCheckInstitute(String checkInstitute) {
        this.checkInstitute = checkInstitute == null ? null : checkInstitute.trim();
    }

    public String getCheckObject() {
        return checkObject;
    }

    public void setCheckObject(String checkObject) {
        this.checkObject = checkObject == null ? null : checkObject.trim();
    }

    public String getProblemInfo() {
        return problemInfo;
    }

    public void setProblemInfo(String problemInfo) {
        this.problemInfo = problemInfo == null ? null : problemInfo.trim();
    }

    public String getSuperviseMeasure() {
        return superviseMeasure;
    }

    public void setSuperviseMeasure(String superviseMeasure) {
        this.superviseMeasure = superviseMeasure == null ? null : superviseMeasure.trim();
    }

    public String getRectifyClaim() {
        return rectifyClaim;
    }

    public void setRectifyClaim(String rectifyClaim) {
        this.rectifyClaim = rectifyClaim == null ? null : rectifyClaim.trim();
    }

    public String getRectifyStaff() {
        return rectifyStaff;
    }

    public void setRectifyStaff(String rectifyStaff) {
        this.rectifyStaff = rectifyStaff == null ? null : rectifyStaff.trim();
    }

    public String getRectifyTerm() {
        return rectifyTerm;
    }

    public void setRectifyTerm(String rectifyTerm) {
        this.rectifyTerm = rectifyTerm == null ? null : rectifyTerm.trim();
    }

    public String getRectifyStatus() {
        return rectifyStatus;
    }

    public void setRectifyStatus(String rectifyStatus) {
        this.rectifyStatus = rectifyStatus == null ? null : rectifyStatus.trim();
    }

    public String getRectifyConclusion() {
        return rectifyConclusion;
    }

    public void setRectifyConclusion(String rectifyConclusion) {
        this.rectifyConclusion = rectifyConclusion == null ? null : rectifyConclusion.trim();
    }

    @Override
    public String toString() {
        return "OutSupervise{" +
                "id='" + id + '\'' +
                ", busiType='" + busiType + '\'' +
                ", datetime='" + datetime + '\'' +
                ", checkName='" + checkName + '\'' +
                ", checkInstitute='" + checkInstitute + '\'' +
                ", checkObject='" + checkObject + '\'' +
                ", problemInfo='" + problemInfo + '\'' +
                ", superviseMeasure='" + superviseMeasure + '\'' +
                ", rectifyClaim='" + rectifyClaim + '\'' +
                ", rectifyStaff='" + rectifyStaff + '\'' +
                ", rectifyTerm='" + rectifyTerm + '\'' +
                ", rectifyStatus='" + rectifyStatus + '\'' +
                ", rectifyConclusion='" + rectifyConclusion + '\'' +
                '}';
    }
}