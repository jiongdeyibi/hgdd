package com.dd.util;

import com.base.IllegalParameterException;

import java.io.*;
import java.util.zip.*;

public class ZipCompressor {
    static final int BUFFER = 8192;

    public byte[] compress(String[] fileName, String[] filePath, String zipFileName) {
        try {
            byte[] tempBytes = null;
            ByteArrayOutputStream tempByteOStream = new ByteArrayOutputStream();
            CheckedOutputStream cos = new CheckedOutputStream(tempByteOStream, new Adler32());
            ZipOutputStream out = new ZipOutputStream(cos);
            BufferedOutputStream tempBufferOStream = new BufferedOutputStream(out);
            for (int i = 0; i < filePath.length; i++) {
                if (i > 0 && fileName[i - 1].equals(fileName[i])) {
                    continue;
                }
                compress(fileName[i], new File(filePath[i]), out, zipFileName);
            }
            tempBufferOStream.flush();
            tempByteOStream.flush();
            out.closeEntry();
            out.close();
            tempBytes = tempByteOStream.toByteArray();
            tempByteOStream.close();
            tempBufferOStream.close();
            return tempBytes;
        } catch (Exception e) {
            throw new IllegalParameterException("压缩失败！");
        }
    }

    private void compress(String fileName, File file, ZipOutputStream out, String zipFileName) {
        /* 判断是目录还是文件 */
        if (file.isDirectory()) {
            //compressDirectory(file, out);     
        } else {
            compressFile(fileName, file, out, zipFileName);
        }
    }

    /**
     * 压缩一个文件
     */
    private void compressFile(String fileName, File file, ZipOutputStream out, String zipFileName) {
        if (!file.exists()) {
            return;
        }
        try {
            InputStream bis = new FileInputStream(file);
            ZipEntry entry = new ZipEntry(zipFileName + "/" + fileName);
            out.putNextEntry(entry);
            int count;
            byte[] data = new byte[BUFFER];
            while ((count = bis.read(data, 0, BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            bis.close();
        } catch (Exception e) {
            throw new IllegalParameterException("压缩失败！");
        }
    }

    /**
     * 压缩一个目录
     */
//    private static void compressDirectory(File dir, ZipOutputStream out) {     
//        if (!dir.exists())     
//            return;       
//        File[] files = dir.listFiles();     
//        for (int i = 0; i < files.length; i++) {     
//            /* 递归 */    
//            compress(files[i].getName(),files[i], out);     
//        }     
//    }     
    public static void main(String[] args) {
        String[] fileName = {"ldapconfig.properties"};
        String[] filePath = {"E:/ldapconfig.properties"};

        try {
            ZipInputStream Zin = new ZipInputStream(new FileInputStream(
                    "C:\\Users\\wangwenjun\\Documents\\TFZQ-YJS-JYDY201702200002.zip"));//输入源zip路径
            BufferedInputStream Bin = new BufferedInputStream(Zin);
            String Parent = "C:\\Users\\wangwenjun\\Documents\\TFZQ-YJS-JYDY201702200002"; //输出路径（文件夹目录）
            File Fout = null;
            ZipEntry entry;
            try {
                while ((entry = Zin.getNextEntry()) != null && !entry.isDirectory()) {
                    Fout = new File(Parent, entry.getName());
                    if (!Fout.exists()) {
                        (new File(Fout.getParent())).mkdirs();
                    }
                    FileOutputStream out = new FileOutputStream(Fout);
                    BufferedOutputStream Bout = new BufferedOutputStream(out);
                    int b;
                    while ((b = Bin.read()) != -1) {
                        Bout.write(b);
                    }
                    Bout.close();
                    out.close();
                    System.out.println(Fout + "解压成功");
                }
                Bin.close();
                Zin.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
