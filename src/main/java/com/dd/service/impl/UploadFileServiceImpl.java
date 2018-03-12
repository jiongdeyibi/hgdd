package com.dd.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.BaseServiceImpl;
import com.dd.entity.UploadFile;
import com.dd.service.UploadFileService;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@Service
@Transactional(readOnly = true)
public class UploadFileServiceImpl extends BaseServiceImpl<UploadFile> implements UploadFileService {	
	
}
