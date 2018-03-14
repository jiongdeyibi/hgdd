package com.dd.entity;

import com.base.BaseModel;

public class ComLetter extends BaseModel {
    private String id;

    private String letterType;

    private String datetime;

    private String warningReason;

    private String warningObject;

    private String treatMeasure;

    private String finishStatus;

    private String otherRemark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLetterType() {
        return letterType;
    }

    public void setLetterType(String letterType) {
        this.letterType = letterType == null ? null : letterType.trim();
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime == null ? null : datetime.trim();
    }

    public String getWarningReason() {
        return warningReason;
    }

    public void setWarningReason(String warningReason) {
        this.warningReason = warningReason == null ? null : warningReason.trim();
    }

    public String getWarningObject() {
        return warningObject;
    }

    public void setWarningObject(String warningObject) {
        this.warningObject = warningObject == null ? null : warningObject.trim();
    }

    public String getTreatMeasure() {
        return treatMeasure;
    }

    public void setTreatMeasure(String treatMeasure) {
        this.treatMeasure = treatMeasure == null ? null : treatMeasure.trim();
    }

    public String getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(String finishStatus) {
        this.finishStatus = finishStatus == null ? null : finishStatus.trim();
    }

    public String getOtherRemark() {
        return otherRemark;
    }

    public void setOtherRemark(String otherRemark) {
        this.otherRemark = otherRemark == null ? null : otherRemark.trim();
    }

    @Override
    public String toString() {
        return "ComLetter{" +
                "id='" + id + '\'' +
                ", letterType='" + letterType + '\'' +
                ", datetime='" + datetime + '\'' +
                ", warningReason='" + warningReason + '\'' +
                ", warningObject='" + warningObject + '\'' +
                ", treatMeasure='" + treatMeasure + '\'' +
                ", finishStatus='" + finishStatus + '\'' +
                ", otherRemark='" + otherRemark + '\'' +
                '}';
    }
}