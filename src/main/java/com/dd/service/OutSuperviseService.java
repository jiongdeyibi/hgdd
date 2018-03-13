package com.dd.service;

import com.base.BaseService;
import com.dd.entity.OutSupervise;
import com.dd.entity.entityBean.OutSuperviseBean;

import java.util.List;
import java.util.Map;

public interface OutSuperviseService extends BaseService<OutSupervise> {
    int insertWithFiles(OutSupervise record,String[] tempFiles);

    List listBean(Map<String, Object> params);

    OutSuperviseBean selectBean(String id);
}
