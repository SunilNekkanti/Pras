package com.pfchoice.core.dao;

import com.pfchoice.core.entity.AttPhysician;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface AttPhysicianDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	AttPhysician deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	AttPhysician findById(Integer id);

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
	AttPhysician save(AttPhysician bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	AttPhysician updateByUpdater(Updater<AttPhysician> updater);

	/**
	 * @param fileId
	 * @return
	 */
	Integer loadData(Integer fileId);

	/**
	 * @param code
	 * @return
	 */
	AttPhysician findByCode(String code);
}
