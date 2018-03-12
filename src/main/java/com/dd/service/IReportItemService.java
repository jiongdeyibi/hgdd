package com.dd.service;

import java.util.List;

import com.dd.entity.ReportItem;

public interface IReportItemService {

	/**
	 * @param id
	 * @return
	 */
	public ReportItem selectByPrimaryKey(String id);


	/**
	 * @param object
	 * @return
	 */
	public int create(ReportItem object);
	
	/**
	 * @param id
	 * @return
	 */
	public int delete(String id);
	
	/**
	 * @param object
	 * @return
	 */
	public int update(ReportItem object);
	
	/**
	 * @param reportId
	 * @return
	 */
	public List<ReportItem> selectByReportId(String reportId);

	/**
	 * @param objectList
	 * @return
	 */
	public int batchCreate(List<ReportItem> objectList);

	/**
	 * @param objectList
	 * @return
	 */
	public int batchUpdate(List<ReportItem> objectList);

	/**
	 * @param object
	 * @return
	 */
	public int updateWithNull(ReportItem object);

	/**
	 * @param objectList
	 * @return
	 */
	public int batchDelete(List<ReportItem> objectList);
}
