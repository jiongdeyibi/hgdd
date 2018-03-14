package com.dd.mapper;

import com.base.BaseMapper;
import com.dd.entity.RiskEvent;
import com.dd.entity.entityBean.RiskEventBean;

import java.util.List;
import java.util.Map;

public interface RiskEventMapper extends BaseMapper<RiskEvent> {
    List listBean(Map<String, Object> params);

    RiskEventBean selectBean(String id);
}