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
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

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

}
