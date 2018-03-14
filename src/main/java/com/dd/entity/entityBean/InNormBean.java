package com.dd.entity.entityBean;

import com.dd.entity.InNorm;
import com.dd.entity.UploadFile;

import java.util.List;

public class InNormBean extends InNorm {
    private List<UploadFile> uploadFileList;

    public List<UploadFile> getUploadFileList() {
        return uploadFileList;
    }

    public void setUploadFileList(List<UploadFile> uploadFileList) {
        this.uploadFileList = uploadFileList;
    }
}
