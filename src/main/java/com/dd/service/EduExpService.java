package com.dd.service;

import java.util.List;
import java.util.Map;
import com.base.BaseService;
import com.dd.entity.EduExp;
import com.dd.entity.EduExp;

public interface EduExpService extends BaseService<EduExp>{

    public int updateByPrimaryKeySelective(EduExp record);

    public List listByEmployeeId(String employeeId);
}
