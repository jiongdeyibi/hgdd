package com.dd.service;

import java.util.List;
import java.util.Map;
import com.dd.entity.ReportItemFile;

public interface IReportItemFileService {

	/**
	 * @param id
	 * @return
	 */
	public ReportItemFile selectByPrimaryKey(String id);

	/**
	 * @param object
	 * @return
	 */
	public int create(ReportItemFile object);
	
	/**
	 * @param id
	 * @return
	 */
	public int delete(String id);
	
	/**
	 * @param object
	 * @return
	 */
	public int update(ReportItemFile object);
	
	/**
	 * @param params
	 * @param compareMap
	 * @return
	 */
	public List list(Map params, Map compareMap);

	public List listByReportItem(String reportItemId);

	/**
	 * @param objectList
	 * @return
	 */
	public int batchCreate(List<ReportItemFile> objectList);

	/**
	 * @param objectList
	 * @return
	 */
	public int batchUpdate(List<ReportItemFile> objectList);

	/**
	 * @param object
	 * @return
	 */
	public int updateWithNull(ReportItemFile object);

	/**
	 * @param objectList
	 * @return
	 */
	public int batchDelete(List<ReportItemFile> objectList);
}
