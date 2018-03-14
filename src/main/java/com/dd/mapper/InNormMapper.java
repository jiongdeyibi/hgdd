package com.dd.mapper;

import com.base.BaseMapper;
import com.dd.entity.InNorm;
import com.dd.entity.entityBean.InNormBean;

import java.util.List;
import java.util.Map;

public interface InNormMapper extends BaseMapper<InNorm> {
    List listBean(Map<String, Object> params);

    InNormBean selectBean(String id);
}