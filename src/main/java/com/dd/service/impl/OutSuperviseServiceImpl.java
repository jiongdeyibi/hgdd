package com.dd.service.impl;

import com.base.BaseServiceImpl;
import com.dd.entity.OutSupervise;
import com.dd.entity.UploadFile;
import com.dd.mapper.OutSuperviseMapper;
import com.dd.mapper.UploadFileMapper;
import com.dd.service.OutSuperviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OutSuperviseServiceImpl extends BaseServiceImpl<OutSupervise> implements OutSuperviseService {
    @Autowired
    OutSuperviseMapper mapper;

    @Autowired
    UploadFileMapper uploadFileMapper;

    @Override
    public int insertFiles(String[] tempFiles,String tableId) {
        for(String str:tempFiles){
            UploadFile uploadFile=uploadFileMapper.selectByPrimaryKey(str);
            uploadFile.setTableId(tableId);
            uploadFileMapper.updateByPrimaryKeySelective(uploadFile);
        }
        return tempFiles.length;
    }

    @Override
    public List listBean(Map<String, Object> params) {
        return mapper.listBean(params);
    }

}
