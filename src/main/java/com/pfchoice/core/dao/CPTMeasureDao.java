package com.pfchoice.core.dao;

import com.pfchoice.core.entity.CPTMeasure;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface CPTMeasureDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	CPTMeasure deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	CPTMeasure findById(Integer id);

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
	CPTMeasure save(CPTMeasure bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	CPTMeasure updateByUpdater(Updater<CPTMeasure> updater);

}
