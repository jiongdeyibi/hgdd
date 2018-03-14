package com.dd.service.impl;

import com.base.BaseServiceImpl;
import com.dd.entity.InNorm;
import com.dd.entity.UploadFile;
import com.dd.entity.entityBean.InNormBean;
import com.dd.mapper.InNormMapper;
import com.dd.mapper.UploadFileMapper;
import com.dd.service.InNormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class InNormServiceImpl extends BaseServiceImpl<InNorm> implements InNormService {
    @Autowired
    InNormMapper mapper;

    @Autowired
    UploadFileMapper uploadFileMapper;

    @Override
    @Transactional
    public int insertWithFiles(InNorm record, String[] tempFiles) {
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
    public InNormBean selectBean(String id) {
        return mapper.selectBean(id);
    }

}
