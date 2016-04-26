package com.pfchoice.core.dao;

import com.pfchoice.core.entity.HedisMeasure;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface HedisMeasureDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	HedisMeasure deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	HedisMeasure findById(Integer id);

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
	HedisMeasure save(HedisMeasure bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	HedisMeasure updateByUpdater(Updater<HedisMeasure> updater);

}
