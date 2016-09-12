package com.pfchoice.core.dao;

import com.pfchoice.core.entity.MedicalLossRatio;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface MedicalLossRatioDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	MedicalLossRatio deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	MedicalLossRatio findById(Integer id);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

	/**
	 * 
	 * @param bean
	 * @return
	 */
	MedicalLossRatio save(MedicalLossRatio bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	MedicalLossRatio updateByUpdater(Updater<MedicalLossRatio> updater);

	/**
	 * @param fileId
	 * @return
	 */
	Integer loadData(Integer fileId);

	/**
	 * @param code
	 * @return
	 */
	MedicalLossRatio findByCode(String code);
}
