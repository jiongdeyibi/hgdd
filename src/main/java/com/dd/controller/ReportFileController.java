package com.dd.controller;


import com.base.BaseController;
import com.base.HttpCode;
import com.dd.entity.ReportItemFile;
import com.dd.service.IReportItemFileService;
import com.dd.util.DateUtil;
import com.dd.util.PropertyUtil;
import com.dd.util.UploadUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by Dtj on 2017/11/20.
 */
@RestController
@RequestMapping(value = "/reportfile")
public class ReportFileController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ReportFileController.class);
    //UPLOADED_FILE_PATH:service path to save file
    private static final String UPLOADED_FILE_PATH = PropertyUtil.getProperty("fileSavePath");

    @Autowired
    IReportItemFileService iReportItemFileService;

    @RequestMapping(value = "/upload", consumes = "multipart/form-data", method = RequestMethod.POST)
    public ResponseEntity uploadFile(@RequestParam("file") CommonsMultipartFile[] file, @RequestParam("fileType") String[] fileType, @RequestParam("reportItemId") String reportItemId, HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-origin", "*");
        response.setContentType("text/html;charset=utf-8");
        List<Map> result = new ArrayList<>();
        for (int i = 0; i < file.length; ++i) {
            result.add(this.saveUploadFile(file[i], fileType[i], reportItemId, request));
        }
        ResponseEntity responseEntity = new ResponseEntity(result, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/uploadtemp", consumes = "multipart/form-data", method = RequestMethod.POST)
    public ResponseEntity uploadFileTemp(@RequestParam("file") CommonsMultipartFile[] file, @RequestParam("fileType") String[] fileType, @RequestParam("reportItemId") String reportItemId, HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-origin", "*");
        response.setContentType("text/html;charset=utf-8");
        List<Map> result = new ArrayList<>();
        for (int i = 0; i < file.length; ++i) {
            result.add(this.saveUploadFile(file[i], fileType[i], "temp", request));
        }
        ResponseEntity responseEntity = new ResponseEntity(result, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ResponseEntity<byte[]> downloadpost(@RequestParam(value = "fileId", required = false) String fileId) throws IOException {
        //通过fileId获取Attachment的保存路径
        String filePath = getDownloadFile(fileId);
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        //将文件名字恢复为上传时的名字
        String fileOriginalName = filePath.substring(filePath.indexOf("_", filePath.indexOf("_") + 1) + 1);
        fileOriginalName = URLEncoder.encode(fileOriginalName, "UTF-8").replaceAll("\\+", "%20").replaceAll("%28", "\\(").replaceAll("%29", "\\)").replaceAll("%3B", ";").replaceAll("%40", "@").replaceAll("%23", "\\#").replaceAll("%26", "\\&").replaceAll("%2C", "\\,");
        headers.setContentDispositionFormData("attachment", fileOriginalName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        logger.info("download File:" + fileOriginalName);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(HttpServletRequest request, ModelMap modelMap,
                         @RequestParam(value = "attId", required = false) String attId) {
        ReportItemFile record = iReportItemFileService.selectByPrimaryKey(attId);
        if (record != null) {
            iReportItemFileService.delete(attId);
        }
        logger.info("delete ReportItemFile:" + record.toString());
        return setSuccessModelMap(modelMap);
    }

    /**
     * 把时间转换成指定格式
     *
     * @param file         传入的文件名  例如:abc.txt
     * @param fileType     文件类型
     * @param reportItemId 报告id
     * @return returnPath 最后保存时的文件名 例如：D:\11\114122_abc.txt
     */
    public Map saveUploadFile(CommonsMultipartFile file, String fileType, String reportItemId, HttpServletRequest request) {
        Map<String, String> returnMap = new HashMap<>();
        if (!file.isEmpty()) {
            String path = UPLOADED_FILE_PATH;

            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
                logger.info("mkdir:" + dir);
            }
            String yearmonth = DateUtil.format(new Date(), "yyyyMMdd");
            String clockstamp = DateUtil.format(new Date(), DateUtil.DATE_PATTERN.HHMMSS);
            path = path + yearmonth + "_" + clockstamp + "_" + file.getOriginalFilename();
            //withoutPath:不带C盘路径的文件名
            String withoutPath=yearmonth + "_" + clockstamp + "_" + file.getOriginalFilename();
            File localFile = new File(path);
            String attId = UUID.randomUUID().toString().replaceAll("-", "");
            iReportItemFileService.create(new ReportItemFile(attId, fileType, withoutPath, reportItemId));

            //save files in dir path
            try {
                file.transferTo(localFile);
                returnMap.put("fileAttId", attId);
                returnMap.put("filePath", withoutPath);
                logger.info("save upload files:" + path);
            } catch (IllegalStateException | IOException e) {
                logger.error("save upload files fail:");
                e.printStackTrace();
            }

        } else {
            logger.info("empty file, do nothing:   " + file.getOriginalFilename());
        }
        return returnMap;
    }

    private String getDownloadFile(String fileId) {
        ReportItemFile reportItemFile;
        reportItemFile = iReportItemFileService.selectByPrimaryKey(fileId);
        String filePath = UPLOADED_FILE_PATH + reportItemFile.getFilePath();
        return filePath;
    }
}
