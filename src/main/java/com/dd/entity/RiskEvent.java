package com.dd.entity;

import com.base.BaseModel;

public class RiskEvent extends BaseModel {
    private String id;

    private String busiType;

    private String datetime;

    private String problemInfo;

    private String checkObject;

    private String problemReason;

    private String riskDisposal;

    private String rectifyPlan;

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

    public String getProblemInfo() {
        return problemInfo;
    }

    public void setProblemInfo(String problemInfo) {
        this.problemInfo = problemInfo == null ? null : problemInfo.trim();
    }

    public String getCheckObject() {
        return checkObject;
    }

    public void setCheckObject(String checkObject) {
        this.checkObject = checkObject == null ? null : checkObject.trim();
    }

    public String getProblemReason() {
        return problemReason;
    }

    public void setProblemReason(String problemReason) {
        this.problemReason = problemReason == null ? null : problemReason.trim();
    }

    public String getRiskDisposal() {
        return riskDisposal;
    }

    public void setRiskDisposal(String riskDisposal) {
        this.riskDisposal = riskDisposal == null ? null : riskDisposal.trim();
    }

    public String getRectifyPlan() {
        return rectifyPlan;
    }

    public void setRectifyPlan(String rectifyPlan) {
        this.rectifyPlan = rectifyPlan == null ? null : rectifyPlan.trim();
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
        return "RiskEvent{" +
                "id='" + id + '\'' +
                ", busiType='" + busiType + '\'' +
                ", datetime='" + datetime + '\'' +
                ", problemInfo='" + problemInfo + '\'' +
                ", checkObject='" + checkObject + '\'' +
                ", problemReason='" + problemReason + '\'' +
                ", riskDisposal='" + riskDisposal + '\'' +
                ", rectifyPlan='" + rectifyPlan + '\'' +
                ", rectifyTerm='" + rectifyTerm + '\'' +
                ", rectifyStatus='" + rectifyStatus + '\'' +
                ", rectifyConclusion='" + rectifyConclusion + '\'' +
                '}';
    }
}