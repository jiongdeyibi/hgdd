package com.dd.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.base.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EduExp extends BaseModel {
    private String eduExpId;

    private String employeeId;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    @JSONField(format="yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    @JSONField(format="yyyy-MM-dd")
    private Date endDate;

    private String school;

    private String major;

    private String degree;

    public String getEduExpId() {
        return eduExpId;
    }

    public void setEduExpId(String eduExpId) {
        this.eduExpId = eduExpId == null ? null : eduExpId.trim();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId == null ? null : employeeId.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    @Override
    public String toString() {
        return "EduExp{" +
                "eduExpId='" + eduExpId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", degree='" + degree + '\'' +
                '}';
    }
}