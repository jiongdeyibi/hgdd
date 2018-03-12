package com.dd.controller;

import com.base.BaseController;
import com.dd.entity.OutSupervise;
import com.dd.service.OutSuperviseService;
import com.dd.util.DictItem;
import com.dd.util.Request2ModelUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/out", method = RequestMethod.POST)
public class OutSuperviseController extends BaseController {

    @Autowired
    private OutSuperviseService service;

    // 详细信息
    @RequestMapping(value = "/detail")
    public Object detail(HttpServletRequest request, ModelMap modelMap) {
        OutSupervise record;
        try {
            record = Request2ModelUtil.covert(OutSupervise.class, request);
        } catch (Exception e) {
            record = null;
            e.printStackTrace();
        }
        if(StringUtils.isNotEmpty(record.getId())){
            record = service.selectByPrimaryKey(record.getId());
        }
        return setSuccessModelMap(modelMap, record);
    }

    /** 新增
     *
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(HttpServletRequest request, ModelMap modelMap) {
        OutSupervise record;
        try {
            record = Request2ModelUtil.covert(OutSupervise.class, request);
        } catch (Exception e) {
            record = null;
            e.printStackTrace();
        }
        service.insert(record);
        return setSuccessModelMap(modelMap);
    }


}

