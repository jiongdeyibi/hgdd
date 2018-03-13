package com.dd.mapper;

import com.base.BaseMapper;
import com.dd.entity.OutSupervise;

import java.util.List;
import java.util.Map;

public interface OutSuperviseMapper extends BaseMapper<OutSupervise> {
    List listBean(Map<String, Object> params);
}