package com.dd.service.impl;

import com.base.BaseServiceImpl;
import com.dd.entity.EduExp;
import com.dd.mapper.EduExpMapper;
import com.dd.service.EduExpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EduExpServiceImpl extends BaseServiceImpl<EduExp> implements EduExpService {
    @Autowired
    EduExpMapper mapper;

    @Override
    public List listByEmployeeId(String employeeId) {
        return mapper.listByEmployeeId(employeeId);
    }
}
