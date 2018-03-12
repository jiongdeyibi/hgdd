package com.dd.entity.entityBean;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CompanyExport {
    /**
     * @author
     */

    private String stockCode;
    private String companyName;
    private String companyType;
    private String legalRepr;
    private String busiLicense;
    private String orgCode;
    private String creditCode;
    private Double totlaStock;
    private String unitStock;
    private Double regCapital;
    private String unitCapital;
    private String busiScope;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date establishDate;
    private String regAddress1;
    private String regAddress2;
    private String busiStatus;
    private String chapter;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getLegalRepr() {
        return legalRepr;
    }

    public void setLegalRepr(String legalRepr) {
        this.legalRepr = legalRepr;
    }

    public String getBusiLicense() {
        return busiLicense;
    }

    public void setBusiLicense(String busiLicense) {
        this.busiLicense = busiLicense;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public Double getTotlaStock() {
        return totlaStock;
    }

    public void setTotlaStock(Double totlaStock) {
        this.totlaStock = totlaStock;
    }

    public Double getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(Double regCapital) {
        this.regCapital = regCapital;
    }

    public String getBusiScope() {
        return busiScope;
    }

    public void setBusiScope(String busiScope) {
        this.busiScope = busiScope;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public String getRegAddress1() {
        return regAddress1;
    }

    public void setRegAddress1(String regAddress1) {
        this.regAddress1 = regAddress1;
    }

    public String getRegAddress2() {
        return regAddress2;
    }

    public void setRegAddress2(String regAddress2) {
        this.regAddress2 = regAddress2;
    }

    public String getBusiStatus() {
        return busiStatus;
    }

    public void setBusiStatus(String busiStatus) {
        this.busiStatus = busiStatus;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getUnitStock() {
        return unitStock;
    }

    public void setUnitStock(String unitStock) {
        this.unitStock = unitStock;
    }

    public String getUnitCapital() {
        return unitCapital;
    }

    public void setUnitCapital(String unitCapital) {
        this.unitCapital = unitCapital;
    }
}
