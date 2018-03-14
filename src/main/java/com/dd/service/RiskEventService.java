package com.dd.service;

import com.base.BaseService;
import com.dd.entity.RiskEvent;
import com.dd.entity.entityBean.RiskEventBean;

import java.util.List;
import java.util.Map;

public interface RiskEventService extends BaseService<RiskEvent> {
    int insertWithFiles(RiskEvent record, String[] tempFiles);

    List listBean(Map<String, Object> params);

    RiskEventBean selectBean(String id);
}
