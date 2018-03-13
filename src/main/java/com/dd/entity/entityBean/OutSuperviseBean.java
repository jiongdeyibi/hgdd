package com.dd.entity.entityBean;

import com.dd.entity.OutSupervise;
import com.dd.entity.UploadFile;

import java.util.List;

public class OutSuperviseBean extends OutSupervise{
    private List<UploadFile> uploadFileList;

    public List<UploadFile> getUploadFileList() {
        return uploadFileList;
    }

    public void setUploadFileList(List<UploadFile> uploadFileList) {
        this.uploadFileList = uploadFileList;
    }
}
