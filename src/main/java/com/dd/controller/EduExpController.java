package com.dd.controller;


import com.base.BaseController;
import com.base.HttpCode;
import com.dd.entity.EduExp;

import com.dd.service.EduExpService;
import com.dd.util.DictItem;
import com.dd.util.Request2ModelUtil;
import com.dd.util.UUIDUtils;
import com.dd.util.WebUtil;
import com.github.pagehelper.PageInfo;
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

/**
 *
 *
 * @author
 */
@RestController
@RequestMapping(value = "/eduexp", method = RequestMethod.POST)
public class EduExpController extends BaseController {
    @Autowired
    private EduExpService eduExpService;

    //详细信息
    @RequestMapping(value = "/detail")
    public Object detail(ModelMap modelMap, @RequestParam(value = "eduExpId", required = false) String eduExpId) {
        EduExp eduExpBean = new EduExp();
        if(StringUtils.isNotEmpty(eduExpId)){
            EduExp record = eduExpService.selectByPrimaryKey(eduExpId);
            try {
                BeanUtils.copyProperties(eduExpBean, record);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e) {
                eduExpBean = null;
                e.printStackTrace();
            }
        }
        logger.info("detail EduExp:"+eduExpBean.toString());
        if(null==eduExpBean){
            setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
        }
        return setSuccessModelMap(modelMap, eduExpBean);
    }

    // 新增
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(HttpServletRequest request, ModelMap modelMap) {
        EduExp record = new EduExp();
        try {
            record = Request2ModelUtil.covert(EduExp.class,request);
        } catch (Exception e) {
            record = null;
            e.printStackTrace();
        }
        record.setEduExpId(UUIDUtils.getUUID());
        eduExpService.insert(record);
        logger.info("add EduExp:"+record.toString());
        return setSuccessModelMap(modelMap);
    }

    // 修改
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(HttpServletRequest request, ModelMap modelMap) {
        EduExp record = new EduExp();
        try {
            record = Request2ModelUtil.covert(EduExp.class,request);
        } catch (Exception e) {
            record = null;
            e.printStackTrace();
        }
        logger.info("updateByPrimaryKeySelective EduExp:"+record.toString());
        eduExpService.updateByPrimaryKeySelective(record);
        return setSuccessModelMap(modelMap);
    }

    // 删除
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(HttpServletRequest request, ModelMap modelMap,
                         @RequestParam(value = "eduExpId", required = false) String eduExpId) {
        EduExp record = eduExpService.selectByPrimaryKey(eduExpId);
        if (record != null) {
            eduExpService.deleteByPrimaryKey(eduExpId);
        }
        logger.info("delete EduExp:"+record.toString());
        return setSuccessModelMap(modelMap);
    }
}