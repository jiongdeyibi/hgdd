package com.dd.service.impl;

import com.dd.entity.ReportItem;
import com.dd.mapper.ReportItemMapper;
import com.dd.service.IReportItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReportItemServiceImpl implements IReportItemService {

	@Autowired
	private ReportItemMapper mapper;

	@Override
	public ReportItem selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	
	@Override
	@Transactional
	public int create(ReportItem object) {
		return mapper.insert(object);
	}
	
	@Override
	@Transactional
	public int delete(String id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	@Override
	@Transactional
	public int update(ReportItem object) {
		return mapper.updateByPrimaryKeySelective(object);
	}

	@Override
	public List<ReportItem> selectByReportId(String reportId) {
		return mapper.selectByReportId(reportId);
	}

	@Override
	public int batchCreate(List<ReportItem> objectList) {
		return 0;
	}

	@Override
	public int batchUpdate(List<ReportItem> objectList) {
		return 0;
	}

	@Override
	public int updateWithNull(ReportItem object) {
		return 0;
	}

	@Override
	public int batchDelete(List<ReportItem> objectList) {
		return 0;
	}

}
