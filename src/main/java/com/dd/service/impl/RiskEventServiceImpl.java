package com.dd.service.impl;

import com.base.BaseServiceImpl;
import com.dd.entity.RiskEvent;
import com.dd.entity.UploadFile;
import com.dd.entity.entityBean.RiskEventBean;
import com.dd.mapper.RiskEventMapper;
import com.dd.mapper.UploadFileMapper;
import com.dd.service.RiskEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RiskEventServiceImpl extends BaseServiceImpl<RiskEvent> implements RiskEventService {
    @Autowired
    RiskEventMapper mapper;

    @Autowired
    UploadFileMapper uploadFileMapper;

    @Override
    @Transactional
    public int insertWithFiles(RiskEvent record, String[] tempFiles) {
        if (null != tempFiles && tempFiles.length > 0) {
            for (String str : tempFiles) {
                UploadFile uploadFile = uploadFileMapper.selectByPrimaryKey(str);
                uploadFile.setTableId(record.getId());
                uploadFileMapper.updateByPrimaryKeySelective(uploadFile);
            }
        }
        return mapper.insert(record);
    }

    @Override
    public List listBean(Map<String, Object> params) {
        return mapper.listBean(params);
    }

    @Override
    public RiskEventBean selectBean(String id) {
        return mapper.selectBean(id);
    }

}
