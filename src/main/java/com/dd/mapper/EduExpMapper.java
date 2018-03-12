package com.dd.mapper;

import com.base.BaseMapper;
import com.dd.entity.EduExp;

import java.util.List;

public interface EduExpMapper extends BaseMapper<EduExp>{

    List listByEmployeeId(String employeeId);
}