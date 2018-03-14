package com.dd.controller;

import com.alibaba.fastjson.JSON;
import com.base.BaseController;
import com.dd.entity.InNorm;
import com.dd.entity.UploadFile;
import com.dd.entity.entityBean.InNormBean;
import com.dd.service.InNormService;
import com.dd.service.UploadFileService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/innorm", method = RequestMethod.POST)
public class InNormController extends BaseController {

    @Autowired
    private InNormService service;

    @Autowired
    private UploadFileService fileService;

    @RequestMapping(value = "/detail")
    public Object detail(@RequestParam(value = "id", required = false) String id, ModelMap modelMap) {
        InNormBean record = service.selectBean(id);
        Map<String, Object> uploadFileParams = new HashMap<>();
        uploadFileParams.put("tableId", id);
        List<UploadFile> uploadFileList = fileService.findByCondition(uploadFileParams);
        record.setUploadFileList(uploadFileList);
        return setSuccessModelMap(modelMap, record);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(@RequestParam(value = "tempFiles", required = false) String tempFilesString, HttpServletRequest request, ModelMap modelMap) {
        InNorm record = Request2ModelUtil.covert(InNorm.class, request);
        record.setId(UUIDUtils.getUUID());
        String[] tempFiles = JSON.parseObject(tempFilesString, String[].class);
        service.insertWithFiles(record, tempFiles);
        return setSuccessModelMap(modelMap);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(HttpServletRequest request, ModelMap modelMap) {
        InNorm record = Request2ModelUtil.covert(InNorm.class, request);
        service.updateByPrimaryKeySelective(record);
        return setSuccessModelMap(modelMap);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(HttpServletRequest request, ModelMap modelMap) {
        InNorm record = Request2ModelUtil.covert(InNorm.class, request);
        service.deleteByPrimaryKey(record.getId());
        return setSuccessModelMap(modelMap);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object list(HttpServletRequest request, ModelMap modelMap) {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        PageInfo<InNorm> list = service.findByPage(params);
        return setSuccessModelMap(modelMap, list);
    }

    @RequestMapping(value = "/excel", method = RequestMethod.POST)
    public void downloadall(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        String excelName = "内部合规检查.xls";
        String title = "内部合规检查";
        String[] headers = {"id", "业务类型", "时间", "检查名称", "检查对象", "问题描述", "整改要求或措施", "整改对接部门/人员", "整改期限", "整改进度", "整改结论"};
        ExportExcel<InNorm> ex = new ExportExcel<>();
        List<InNorm> list = new ArrayList<>();
        list = service.findByCondition(params);
        //导出时将id修改为序号
        EXCEL_INDEX = 1;
        list.forEach((InNorm l) -> {
            l.setId(String.valueOf(EXCEL_INDEX++));
        });
        setResponse(response, excelName);
        excuResponse(response, ex, title, headers, list);
        logger.info("内部合规检查 excel导出成功！");

    }
}

