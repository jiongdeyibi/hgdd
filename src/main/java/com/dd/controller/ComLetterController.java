package com.dd.controller;

import com.alibaba.fastjson.JSON;
import com.base.BaseController;
import com.dd.entity.ComLetter;
import com.dd.service.ComLetterService;
import com.dd.util.ExportExcel;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/comletter", method = RequestMethod.POST)
public class ComLetterController extends BaseController {

    @Autowired
    private ComLetterService service;

    @RequestMapping(value = "/detail")
    public Object detail(@RequestParam(value = "id", required = false) String id, ModelMap modelMap) {
        ComLetter record = service.selectByPrimaryKey(id);
        return setSuccessModelMap(modelMap, record);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(@RequestParam(value = "tempFiles", required = false) String tempFilesString, HttpServletRequest request, ModelMap modelMap) {
        ComLetter record = Request2ModelUtil.covert(ComLetter.class, request);
        record.setId(UUIDUtils.getUUID());
        String[] tempFiles = JSON.parseObject(tempFilesString, String[].class);
        service.insertWithFiles(record, tempFiles);
        return setSuccessModelMap(modelMap);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(HttpServletRequest request, ModelMap modelMap) {
        ComLetter record = Request2ModelUtil.covert(ComLetter.class, request);
        service.updateByPrimaryKeySelective(record);
        return setSuccessModelMap(modelMap);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(HttpServletRequest request, ModelMap modelMap) {
        ComLetter record = Request2ModelUtil.covert(ComLetter.class, request);
        service.deleteByPrimaryKey(record.getId());
        return setSuccessModelMap(modelMap);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object list(HttpServletRequest request, ModelMap modelMap) {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        PageInfo<ComLetter> list = service.findByPage(params);
        return setSuccessModelMap(modelMap, list);
    }

    @RequestMapping(value = "/excel", method = RequestMethod.POST)
    public void downloadall(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        String excelName = "合规函件.xls";
        String title = "合规函件";
        String[] headers = {"id", "函件类型", "时间", "警示理由", "警示对象", "处理措施", "完成情况", "其他说明"};
        ExportExcel<ComLetter> ex = new ExportExcel<>();
        List<ComLetter> list = new ArrayList<>();
        list = service.findByCondition(params);
        //导出时将id修改为序号
        EXCEL_INDEX = 1;
        list.forEach((ComLetter l) -> {
            l.setId(String.valueOf(EXCEL_INDEX++));
        });
        setResponse(response, excelName);
        excuResponse(response, ex, title, headers, list);
        logger.info("合规函件 excel导出成功！");

    }
}

