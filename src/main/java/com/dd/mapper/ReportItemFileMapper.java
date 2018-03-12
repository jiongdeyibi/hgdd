package com.dd.mapper;

import com.dd.entity.ReportItemFile;

import java.util.List;

public interface ReportItemFileMapper {
    int deleteByPrimaryKey(String attId);

    int insert(ReportItemFile record);

    int insertSelective(ReportItemFile record);

    ReportItemFile selectByPrimaryKey(String attId);

    int updateByPrimaryKeySelective(ReportItemFile record);

    int updateByPrimaryKey(ReportItemFile record);

    List listByReportItem(String reportItemId);
}