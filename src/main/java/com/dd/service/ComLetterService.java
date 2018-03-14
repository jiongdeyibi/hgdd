package com.dd.service;

import com.base.BaseService;
import com.dd.entity.ComLetter;

public interface ComLetterService extends BaseService<ComLetter> {
    int insertWithFiles(ComLetter record, String[] tempFiles);
}
