package com.dd.entity;

import com.base.BaseModel;

public class OutSupervise extends BaseModel {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}