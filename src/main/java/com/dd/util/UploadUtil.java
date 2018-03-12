package com.dd.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 上传辅助类 与Spring.multipartResolver冲突
 *
 * @author ShenHuaJie
 */
public final class UploadUtil {
    private UploadUtil() {
    }

    /**
     * 上传文件缓存大小限制
     */
    private static int fileSizeThreshold = 1024 * 1024 * 1;
    /**
     * 上传文件临时目录
     */
    public static final String uploadFileDir = PropertyUtil.getProperty("fileSavePath");

    /**
     * 获取所有文本域
     */
    public static final List<?> getFileItemList(HttpServletRequest request, File saveDir) throws FileUploadException {
        if (!saveDir.isDirectory()) {
            saveDir.mkdir();
        }
        List<?> fileItems = null;
        RequestContext requestContext = new ServletRequestContext(request);
        if (FileUpload.isMultipartContent(requestContext)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setRepository(saveDir);
            factory.setSizeThreshold(fileSizeThreshold);
            ServletFileUpload upload = new ServletFileUpload(factory);
            fileItems = upload.parseRequest(request);
        }
        return fileItems;
    }

    /**
     * 获取文本域
     */
    public static final FileItem[] getFileItem(HttpServletRequest request, String saveDir, String... fieldName)
            throws FileUploadException {
        if (fieldName == null) {
            return null;
        }
        if (saveDir == null) {
            saveDir = uploadFileDir;
        }

        List<?> fileItemList = getFileItemList(request, new File(saveDir));
        FileItem fileItem = null;
        FileItem[] fileItems = new FileItem[fieldName.length];
        for (int i = 0; i < fieldName.length; i++) {
            for (Iterator<?> iterator = fileItemList.iterator(); iterator.hasNext(); ) {
                fileItem = (FileItem) iterator.next();
                // 根据名字获得文本域
                if (fieldName[i] != null && fieldName[i].equals(fileItem.getFieldName())) {
                    fileItems[i] = fileItem;
                    break;
                }
            }
        }
        return fileItems;
    }

    /**
     * 上传文件处理(支持批量)
     */
    public static Map<String, String> uploadFile(HttpServletRequest request) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        Map<String, String> fileNames = InstanceUtil.newHashMap();
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = multiRequest.getFileNames();
            String pathDir = uploadFileDir;
            File dirFile = new File(pathDir);
            if (!dirFile.isDirectory()) {
                dirFile.mkdir();
            }
            while (iterator.hasNext()) {
                String key = iterator.next();
                List<MultipartFile> multipartFiles = multiRequest.getFiles(key);
                if (multipartFiles.size() > 0) {
                    for (MultipartFile multipartFile : multipartFiles) {
                        if (multipartFile != null && !multipartFile.isEmpty()) {
                            String name = multipartFile.getOriginalFilename();
                            String postFix = "";
                            if (name.indexOf(".") > 0) {
                                postFix = name.substring(name.lastIndexOf(".")).toLowerCase();
                            }
                            String uuid = UUID.randomUUID().toString();
                            String fileName = uuid + postFix;
                            String filePath = pathDir + File.separator + fileName;
                            File file = new File(filePath);
                            file.setWritable(true, false);
                            try {
                                multipartFile.transferTo(file);
                                fileNames.put(name, fileName);
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }
        }
        return fileNames;
    }

    /**
     * 上传文件处理(支持批量)
     */
    public static List<String> uploadImage(HttpServletRequest request) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        List<String> fileNames = InstanceUtil.newArrayList();
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = multiRequest.getFileNames();
            String pathDir = uploadFileDir;
            File dirFile = new File(pathDir);
            if (!dirFile.isDirectory()) {
                dirFile.mkdir();
            }
            while (iterator.hasNext()) {
                String key = iterator.next();
                MultipartFile multipartFile = multiRequest.getFile(key);
                if (multipartFile != null) {
                    String name = multipartFile.getOriginalFilename();
                    if (name.indexOf(".") == -1 && "blob".equals(name)) {
                        name = name + ".png";
                    }
                    String uuid = UUID.randomUUID().toString();
                    String postFix = name.substring(name.lastIndexOf(".")).toLowerCase();
                    String fileName = uuid + postFix;
                    String filePath = pathDir + File.separator + fileName;
                    File file = new File(filePath);
                    file.setWritable(true, false);
                    try {
                        multipartFile.transferTo(file);
                        fileNames.add(fileName);
                    } catch (Exception e) {
                    }
                    try { // 缩放
                        BufferedImage bufferedImg = ImageIO.read(file);
                        int orgwidth = bufferedImg.getWidth();// 原始宽度
                        ImageUtil.scaleWidth(file, 100);
                        if (orgwidth > 300) {
                            ImageUtil.scaleWidth(file, 300);
                        }
                        if (orgwidth > 500) {
                            ImageUtil.scaleWidth(file, 500);
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        return fileNames;
    }
}
