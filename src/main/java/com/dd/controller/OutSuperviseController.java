package com.dd.controller;

import com.base.BaseController;
import com.dd.entity.OutSupervise;
import com.dd.entity.entityBean.OutSuperviseBean;
import com.dd.service.OutSuperviseService;
import com.dd.service.UploadFileService;
import com.dd.util.Request2ModelUtil;
import com.dd.util.UUIDUtils;
import com.dd.util.WebUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/outsupervise", method = RequestMethod.POST)
public class OutSuperviseController extends BaseController {

    @Autowired
    private OutSuperviseService service;

    @Autowired
    private UploadFileService fileService;

    @RequestMapping(value = "/detail")
    public Object detail(HttpServletRequest request, ModelMap modelMap) {
        OutSupervise record = Request2ModelUtil.covert(OutSupervise.class, request);
        record = service.selectByPrimaryKey(record.getId());
        return setSuccessModelMap(modelMap, record);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(@RequestParam(value = "tempFiles", required = false) String[] tempFiles, HttpServletRequest request, ModelMap modelMap) {
        OutSupervise record = Request2ModelUtil.covert(OutSupervise.class, request);
        record.setId(UUIDUtils.getUUID());
        service.insert(record);
        if(tempFiles.length>0){
            service.insertFiles(tempFiles,record.getId());
        }
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

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object list(HttpServletRequest request, ModelMap modelMap) {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        PageInfo<OutSupervise> list= service.findByPage(params);
        return setSuccessModelMap(modelMap,list);
    }
}

