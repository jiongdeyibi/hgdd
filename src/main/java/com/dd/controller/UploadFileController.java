package com.dd.controller;

import com.base.BaseController;
import com.base.HttpCode;
import com.dd.entity.UploadFile;
import com.dd.service.UploadFileService;
import com.dd.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 文件上传控制器
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:11:42
 */
@RestController
@RequestMapping(value = "/uploadfile")
public class UploadFileController extends BaseController {
    @Autowired
    private UploadFileService uploadFileService;

    // 上传文件(支持批量)
    @RequestMapping(value = "/file")
    public Object uploadFile(MultipartHttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
        response.setHeader("Access-Control-Allow-origin", "*");
        response.setContentType("text/html;charset=utf-8");
        Map<String, String> fileNames = UploadUtil.uploadFile(request);
        if (fileNames.size() > 0) {
            List<UploadFile> files = new ArrayList<UploadFile>();
            for (Entry<String, String> entry : fileNames.entrySet()) {
                UploadFile uploadFile = new UploadFile();
                uploadFile.setActualName(entry.getKey());
                uploadFile.setId(entry.getValue());
                uploadFile.setGroupName("");
                uploadFile.setFileName(entry.getValue());
                uploadFileService.insert(uploadFile);
                files.add(uploadFile);
            }
            modelMap.put("files", files);
            return setSuccessModelMap(modelMap);
        } else {
            setModelMap(modelMap, HttpCode.BAD_REQUEST);
            modelMap.put("msg", "请选择要上传的文件！");
            return modelMap;
        }
    }

    @RequestMapping(value = "/down")
    public String downloadFile(String fileId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
        UploadFile file = uploadFileService.selectByPrimaryKey(fileId);
        try {
            String ctxPath = UploadUtil.uploadFileDir;
            String downLoadPath = ctxPath + File.separator + file.getFileName();
            //设置文件输出类型
            InputStream is = new FileInputStream(downLoadPath);
            OutputStream os = response.getOutputStream();
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + URLEncoder.encode(file.getActualName(), "UTF-8"));
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = is.read(buff, 0, buff.length))) {
                os.write(buff, 0, bytesRead);
            }
            //关闭流
            is.close();
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }
}
