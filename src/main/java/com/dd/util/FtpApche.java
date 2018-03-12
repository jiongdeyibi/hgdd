package com.dd.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

public class FtpApche {
    private static FTPClient ftpClient = new FTPClient();
    private String encoding = System.getProperty("file.encoding");
    private String url = null;
    private Integer port = null;
    private String username = null;
    private String password = null;
    private String path = null;

    public FtpApche(String url, int port, String username, String password, String path) {
        this.url = url;
        this.port = port;
        this.username = username;
        this.password = password;
        this.path = path;
    }

    /**
     * Description: 向FTP服务器上传文件
     *
     * @param url      FTP服务器hostname
     * @param port     FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param path     FTP服务器保存目录,如果是根目录则为“/”
     * @param filename 上传到FTP服务器上的文件名
     * @param input    本地文件输入流
     * @return 成功返回true，否则返回false
     */
    public String uploadFile(String[] filename, String[] filepath) {
        String result = "success";
        try {
            int reply;
            // 检验是否连接成功
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.connect(url, port);
                ftpClient.login(username, password);
                ftpClient.setControlEncoding(encoding);
                if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                    ftpClient.disconnect();
                    return "连接失败";
                }
            }
            ftpClient.makeDirectory(path);
            // 转移工作目录至指定目录下
            boolean change = ftpClient.changeWorkingDirectory(path);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            if (change) {
                for (int i = 0; i < filepath.length; i++) {
                    FileInputStream in = new FileInputStream(new File(filepath[i]));
                    boolean flag = ftpClient.storeFile(new String(filename[i].getBytes(encoding), "iso-8859-1"), in);
                    if (!flag) {
                        return filename[i] + "上传失败!";
                    }
                    in.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 下载
     *
     * @param remotePath 下载文件  	GBK编码
     * @param localPath  本地目录 	ISO-8859-1编码
     * @return
     * @throws Exception
     */
    public String downLoadFile(String[] remoteFile, String localPath) throws Exception {
        String result = "success";
        try {
            int reply;
            // 检验是否连接成功
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.connect(url, port);
                ftpClient.login(username, password);
                ftpClient.setControlEncoding(encoding);
                if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                    ftpClient.disconnect();
                    return "连接失败";
                }
            }
            ftpClient.makeDirectory(path);
            // 转移工作目录至指定目录下
            boolean change = ftpClient.changeWorkingDirectory(path);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            if (change) {
                if (remoteFile.length == 1) {//下载文件
                    FTPFile[] ftpFiles = ftpClient.listFiles(remoteFile[0]);
                    if (ftpFiles.length > 0) {
                        String fileLocalPath = localPath + "/" + new String(ftpFiles[0].getName().getBytes("ISO-8859-1"), "GBK");
                        File localFile = new File(fileLocalPath);
                        if (!localFile.getParentFile().exists()) {
                            localFile.getParentFile().mkdirs();
                        }
                        OutputStream outputStream = new FileOutputStream(localFile);
                        ftpClient.retrieveFile(ftpFiles[0].getName(), outputStream);
                        outputStream.flush();
                        outputStream.close();
                    }
                } else {//下载ZIP

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;


    }

    public void disconnect() {
        if (ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException ioe) {
            }
        }
    }

    public static void main(String[] args) {
        FtpApche fa = new FtpApche("10.105.70.23", 21, "", "", "E:\\王");
        try {
            String[] name = {"王1.txt"};
            String[] path = {"E:/王1.txt"};
            String flag = fa.uploadFile(name, path);
            System.out.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
