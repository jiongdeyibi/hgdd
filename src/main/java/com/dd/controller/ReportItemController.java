package com.dd.controller;

import com.base.BaseController;
import com.base.HttpCode;
import com.dd.entity.ReportItem;
import com.dd.service.IReportItemFileService;
import com.dd.service.IReportItemService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dtj
 * @version 1.0
 * @since
 */
@Controller
@RequestMapping("/reportitem")
public class ReportItemController extends BaseController {

    private static Logger logger = Logger.getLogger(ReportItemController.class);

    @Autowired
    private IReportItemService ReportItemService;


    @Autowired
    private IReportItemFileService reportItemFileService;


    @RequestMapping(value = "/query/{itemId}", method = RequestMethod.GET)
    public Object queryReportItem(@PathVariable("itemId") String id, ModelMap modelMap) {

        ReportItem reportItem = ReportItemService.selectByPrimaryKey(id);
        logger.info("queryReportItem:" + reportItem.toString());
        return setSuccessModelMap(modelMap, reportItem);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object createReportItem(HttpServletRequest request, ModelMap modelMap) {
        ReportItem record = new ReportItem();
        try {
            BeanUtils.populate(record, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            record = null;
            e.printStackTrace();
        }
        int result = ReportItemService.create(record);
        logger.info("createReportItem:" + record.toString());
        if (0 == result) {
            return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
        }
        return setSuccessModelMap(modelMap);
    }

    @RequestMapping(value = "/delete/{itemId}", method = RequestMethod.GET)
    public Object deleteReportItem(@PathVariable("itemId") String id, ModelMap modelMap) {
        int result = ReportItemService.delete(id);
        logger.info("deleteReportItem:" + "itemId=" + id);
        if (0 == result) {
            return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
        }
        return setSuccessModelMap(modelMap);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateReportItem(HttpServletRequest request, ModelMap modelMap) {
        ReportItem record = new ReportItem();
        try {
            BeanUtils.populate(record, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            record = null;
            e.printStackTrace();
        }
        int result = ReportItemService.update(record);
        logger.info("updateReportItem:" + record.toString());
        if (0 == result) {
            return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
        }
        return setSuccessModelMap(modelMap);
    }

    //基本查询
    @RequestMapping(value = "/list/{reportId}", method = RequestMethod.GET)
    public Object listReportItemByReportId(@PathVariable("reportId") String id, ModelMap modelMap) {
        List<ReportItem> list = new ArrayList<ReportItem>();
        list = ReportItemService.selectByReportId(id);
        logger.info("listReportItem:" + list.toString());
        if (null == list) {
            return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
        }
        return setSuccessModelMap(modelMap, list);
    }

}


