package com.dd.service;

import com.base.BaseService;
import com.dd.entity.InNorm;
import com.dd.entity.entityBean.InNormBean;

import java.util.List;
import java.util.Map;

public interface InNormService extends BaseService<InNorm> {
    int insertWithFiles(InNorm record, String[] tempFiles);

    List listBean(Map<String, Object> params);

    InNormBean selectBean(String id);
}
