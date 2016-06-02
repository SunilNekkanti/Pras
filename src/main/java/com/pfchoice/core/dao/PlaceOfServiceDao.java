package com.pfchoice.core.dao;

import com.pfchoice.core.entity.PlaceOfService;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface PlaceOfServiceDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	PlaceOfService deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	PlaceOfService findById(Integer id);

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
	PlaceOfService save(PlaceOfService bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	PlaceOfService updateByUpdater(Updater<PlaceOfService> updater);

	/**
	 * 
	 * @param code
	 * @return
	 */
	PlaceOfService findByCode(String code);
}
