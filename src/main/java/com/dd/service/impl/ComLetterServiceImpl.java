package com.dd.service.impl;

import com.base.BaseServiceImpl;
import com.dd.entity.ComLetter;
import com.dd.entity.UploadFile;
import com.dd.mapper.ComLetterMapper;
import com.dd.mapper.UploadFileMapper;
import com.dd.service.ComLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComLetterServiceImpl extends BaseServiceImpl<ComLetter> implements ComLetterService {
    @Autowired
    ComLetterMapper mapper;

    @Autowired
    UploadFileMapper uploadFileMapper;

    @Override
    @Transactional
    public int insertWithFiles(ComLetter record, String[] tempFiles) {
        if (null != tempFiles && tempFiles.length > 0) {
            for (String str : tempFiles) {
                UploadFile uploadFile = uploadFileMapper.selectByPrimaryKey(str);
                uploadFile.setTableId(record.getId());
                uploadFileMapper.updateByPrimaryKeySelective(uploadFile);
            }
        }
        return mapper.insert(record);
    }

}
