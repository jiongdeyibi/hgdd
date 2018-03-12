package com.dd.mapper;

import com.dd.entity.ReportItem;

import java.util.List;

public interface ReportItemMapper {
    int deleteByPrimaryKey(String reportItemId);

    int insert(ReportItem record);

    int insertSelective(ReportItem record);

    ReportItem selectByPrimaryKey(String reportItemId);


    int updateByPrimaryKeySelective(ReportItem record);

    int updateByPrimaryKey(ReportItem record);

    List<ReportItem> selectByReportId(String reportId);


}