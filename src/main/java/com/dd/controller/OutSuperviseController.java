package com.dd.controller;

import com.base.BaseController;
import com.dd.entity.OutSupervise;
import com.dd.service.OutSuperviseService;
import com.dd.util.Request2ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/out", method = RequestMethod.POST)
public class OutSuperviseController extends BaseController {

    @Autowired
    private OutSuperviseService service;

    @RequestMapping(value = "/detail")
    public Object detail(HttpServletRequest request, ModelMap modelMap) {
        OutSupervise record = Request2ModelUtil.covert(OutSupervise.class, request);
        record = service.selectByPrimaryKey(record.getId());
        return setSuccessModelMap(modelMap, record);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(HttpServletRequest request, ModelMap modelMap) {
        OutSupervise record = Request2ModelUtil.covert(OutSupervise.class, request);
        service.insert(record);
        return setSuccessModelMap(modelMap);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(HttpServletRequest request, ModelMap modelMap) {
        OutSupervise record = Request2ModelUtil.covert(OutSupervise.class, request);
        service.updateByPrimaryKeySelective(record);
        return setSuccessModelMap(modelMap);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(HttpServletRequest request, ModelMap modelMap) {
        OutSupervise record = Request2ModelUtil.covert(OutSupervise.class, request);
        service.deleteByPrimaryKey(record.getId());
        return setSuccessModelMap(modelMap);
    }
}

