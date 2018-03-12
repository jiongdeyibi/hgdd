package com.dd.entity;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.base.BaseModel;
/**
 *   @author  
 */
public class Company extends BaseModel{
	private String unitStock ;
	private String unitCapital  ;
	private String creditCode ;
	private String stockCode;
    private String companyName;
    private String companyType;
    private String companyTypeo;
    private String legalRepr;
    private String busiLicense;
    private String orgCode;
    private Double regCapital;
    private String busiScope;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    @JSONField (format="yyyy-MM-dd")
    private Date establishDate;
    private String regAddress1;
    private String regAddress2;
    private String busiStatus;
    private String chapter;
    private String chapterAttName;
    private String chapterAtt;
    private String documentPic;
    private String documentPicName;
    private String director ;
    private String supervisor ;
    private String seniorManagement ;
    private Double totlaStock;
    
    
    
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
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public String getCompanyName(){
        return this.companyName;
    }
    public void setCompanyType(String companyType){
        this.companyType = companyType;
    }
    public String getCompanyType(){
        return this.companyType;
    }
    public void setCompanyTypeo(String companyTypeo){
        this.companyTypeo = companyTypeo;
    }
    public String getCompanyTypeo(){
        return this.companyTypeo;
    }
    public void setLegalRepr(String legalRepr){
        this.legalRepr = legalRepr;
    }
    public String getLegalRepr(){
        return this.legalRepr;
    }
    public void setBusiLicense(String busiLicense){
        this.busiLicense = busiLicense;
    }
    public String getBusiLicense(){
        return this.busiLicense;
    }
    public void setOrgCode(String orgCode){
        this.orgCode = orgCode;
    }
    public String getOrgCode(){
        return this.orgCode;
    }
    public void setRegCapital(Double regCapital){
        this.regCapital = regCapital;
    }
    public Double getRegCapital(){
        return this.regCapital;
    }
    public void setBusiScope(String busiScope){
        this.busiScope = busiScope;
    }
    public String getBusiScope(){
        return this.busiScope;
    }
    public void setEstablishDate(Date establishDate){
        this.establishDate = establishDate;
    }
    public Date getEstablishDate(){
        return this.establishDate;
    }
    public void setRegAddress1(String regAddress1){
        this.regAddress1 = regAddress1;
    }
    public String getRegAddress1(){
        return this.regAddress1;
    }
    public void setRegAddress2(String regAddress2){
        this.regAddress2 = regAddress2;
    }
    public String getRegAddress2(){
        return this.regAddress2;
    }
    public void setBusiStatus(String busiStatus){
        this.busiStatus = busiStatus;
    }
    public String getBusiStatus(){
        return this.busiStatus;
    }
    public void setChapter(String chapter){
        this.chapter = chapter;
    }
    public String getChapter(){
        return this.chapter;
    }
    public void setChapterAtt(String chapterAtt){
        this.chapterAtt = chapterAtt;
    }
    public String getChapterAtt(){
        return this.chapterAtt;
    }
    public void setDocumentPic(String documentPic){
        this.documentPic = documentPic;
    }
    public String getDocumentPic(){
        return this.documentPic;
    }
    public void setDirector (String director ){
        this.director  = director ;
    }
    public String getDirector (){
        return this.director ;
    }
    public void setSupervisor (String supervisor ){
        this.supervisor  = supervisor ;
    }
    public String getSupervisor (){
        return this.supervisor ;
    }
    public void setSeniorManagement (String seniorManagement ){
        this.seniorManagement  = seniorManagement ;
    }
    public String getSeniorManagement (){
        return this.seniorManagement ;
    }
	public String getChapterAttName() {
		return chapterAttName;
	}
	public void setChapterAttName(String chapterAttName) {
		this.chapterAttName = chapterAttName;
	}
	public String getDocumentPicName() {
		return documentPicName;
	}
	public void setDocumentPicName(String documentPicName) {
		this.documentPicName = documentPicName;
	}
    
}