package com.dd.service;

import com.base.BaseService;
import com.dd.entity.OutSupervise;

import java.util.List;
import java.util.Map;

public interface OutSuperviseService extends BaseService<OutSupervise> {
    int insertFiles(String[] tempFiles,String tableId);

    List listBean(Map<String, Object> params);
}
