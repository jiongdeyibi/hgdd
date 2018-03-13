package com.dd.controller;


import com.base.BaseController;
import com.dd.entity.UploadFile;
import com.dd.service.UploadFileService;
import com.dd.util.DateUtil;
import com.dd.util.PropertyUtil;
import com.dd.util.UUIDUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dtj on 2017/11/20.
 */
@RestController
@RequestMapping(value = "/uploadfile")
public class UploadFileController extends BaseController {
    private static final String UPLOADED_FILE_PATH = PropertyUtil.getProperty("fileSavePath");

    @Autowired
    UploadFileService service;

    @RequestMapping(value = "/upload", consumes = "multipart/form-data", method = RequestMethod.POST)
    public Object uploadFile(@RequestParam("file") CommonsMultipartFile[] file, @RequestParam(value = "tableId", required = false) String tableId, HttpServletResponse response, ModelMap modelMap) {
        response.setHeader("Access-Control-Allow-origin", "*");
        response.setContentType("text/html;charset=utf-8");
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < file.length; ++i) {
            result.add(this.saveUploadFile(file[i], tableId));
        }
        return setSuccessModelMap(modelMap, result);
    }

    @RequestMapping(value = "/uploadtemp", consumes = "multipart/form-data", method = RequestMethod.POST)
    public Object uploadFileTemp(@RequestParam("file") CommonsMultipartFile[] file,HttpServletResponse response, ModelMap modelMap) {
        response.setHeader("Access-Control-Allow-origin", "*");
        response.setContentType("text/html;charset=utf-8");
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < file.length; ++i) {
            result.add(this.saveUploadFile(file[i], "temp"));
        }
        return setSuccessModelMap(modelMap, result);
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ResponseEntity<byte[]> download(@RequestParam(value = "fileId", required = false) String fileId) throws IOException {
        //通过fileId获取Attachment的保存路径
        UploadFile uploadFile = service.selectByPrimaryKey(fileId);
        File file = new File(UPLOADED_FILE_PATH + uploadFile.getDownloadPath());
        if (!file.exists()) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        //将文件名字恢复为上传时的名字
        String fileOriginalName = uploadFile.getFileName();
        logger.info("download File:" + fileOriginalName);
        fileOriginalName = URLEncoder.encode(fileOriginalName, "UTF-8").replaceAll("\\+", "%20").replaceAll("%28", "\\(").replaceAll("%29", "\\)").replaceAll("%3B", ";").replaceAll("%40", "@").replaceAll("%23", "\\#").replaceAll("%26", "\\&").replaceAll("%2C", "\\,");
        headers.setContentDispositionFormData("attachment", fileOriginalName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@RequestParam(value = "fileId", required = false) String fileId, ModelMap modelMap) {
        UploadFile record = service.selectByPrimaryKey(fileId);
        if (record != null) {
            service.deleteByPrimaryKey(fileId);
        }
        logger.info("delete UploadFile:" + record.toString());
        return setSuccessModelMap(modelMap);
    }

    /**
     * 保存文件
     * @param file
     * @param tableId
     * @return
     */
    public String saveUploadFile(CommonsMultipartFile file, String tableId) {
        String result = null;
        if (!file.isEmpty()) {
            String path = UPLOADED_FILE_PATH;

            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
                logger.info("mkdir:" + dir);
            }
            String uuid = UUIDUtils.getUUID();
            path = path + uuid;
            //withoutPath:不带C盘路径的文件名
            String originalFilename = file.getOriginalFilename();
            File localFile = new File(path);
            UploadFile uploadFile = new UploadFile();
            uploadFile.setId(uuid);
            uploadFile.setTableId(tableId);
            uploadFile.setFileName(originalFilename);
            uploadFile.setDownloadPath(uuid);
            uploadFile.setUploadTime(DateUtil.format(new Date(), DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS));
            service.insert(uploadFile);
            try {
                file.transferTo(localFile);
                result = uuid;
                logger.info("save upload files:" + path);
            } catch (IllegalStateException | IOException e) {
                logger.error("save upload files fail:");
                e.printStackTrace();
            }

        } else {
            logger.info("empty file, do nothing:   " + file.getOriginalFilename());
        }
        return result;
    }

}
