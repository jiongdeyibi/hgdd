package com.dd.service;

import com.base.BaseService;
import com.dd.entity.EduExp;

import java.util.List;

public interface EduExpService extends BaseService<EduExp> {

    public int updateByPrimaryKeySelective(EduExp record);

    public List listByEmployeeId(String employeeId);
}
