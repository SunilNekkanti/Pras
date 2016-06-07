package com.pfchoice.core.dao;

import com.pfchoice.core.entity.Hospital;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface HospitalDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	Hospital deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Hospital findById(Integer id);

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
	Hospital save(Hospital bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	Hospital updateByUpdater(Updater<Hospital> updater);

	/**
	 * @param fileId
	 * @return
	 */
	Integer loadData(Integer fileId);

	/**
	 * @param hospitalName
	 * @return
	 */
	Hospital findByName(String hosptialName);
}
