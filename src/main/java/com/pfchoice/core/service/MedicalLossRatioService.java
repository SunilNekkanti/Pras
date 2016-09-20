package com.pfchoice.core.service;

import com.pfchoice.core.entity.MedicalLossRatio;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface MedicalLossRatioService {

	/**
	 * @param id
	 * @return
	 */
	MedicalLossRatio deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	MedicalLossRatio findById(Integer id);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);
	
	
	/**
	 * @param pageNo
	 * @param pageSize
	 * @param insId
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getMlrReportDate(Integer pageNo, Integer pageSize, Integer insId, Integer prvdrId,  String sort, String sortdir);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param insId
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(Integer pageNo, Integer pageSize, Integer insId, Integer prvdrId, String sSearch, String sort, String sortdir);

	/**
	 * @param bean
	 * @return
	 */
	MedicalLossRatio save(MedicalLossRatio bean);

	/**
	 * @param bean
	 * @return
	 */
	MedicalLossRatio update(MedicalLossRatio bean);

	/**
	 * @param fileId
	 * @return
	 */
	Integer loadData(Integer fileId);
	
	/**
	 * @param tableName
	 * @return
	 */
	String reportQuery(String tableName);

}
