package com.dd.service.impl;

import com.dd.entity.ReportItemFile;
import com.dd.mapper.ReportItemFileMapper;
import com.dd.service.IReportItemFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class ReportItemFileServiceImpl implements IReportItemFileService {

    @Autowired
    private ReportItemFileMapper mapper;

    @Override
    public ReportItemFile selectByPrimaryKey(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int create(ReportItemFile object) {
        return mapper.insert(object);
    }

    @Override
    @Transactional
    public int delete(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int update(ReportItemFile object) {
        return mapper.updateByPrimaryKeySelective(object);
    }

    @Override
    public List list(Map params, Map compareMap) {
        return null;
    }

    @Override
    public List listByReportItem(String reportItemId) {
        return mapper.listByReportItem(reportItemId);
    }

    @Override
    public int batchCreate(List objectList) {
        return 0;
    }

    @Override
    public int batchUpdate(List objectList) {
        return 0;
    }

    @Override
    public int updateWithNull(ReportItemFile object) {
        return 0;
    }

    @Override
    public int batchDelete(List objectList) {
        return 0;
    }

}
