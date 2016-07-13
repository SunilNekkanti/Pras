package com.pfchoice.core.dao;

import com.pfchoice.core.entity.PlanType;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface PlanTypeDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	PlanType deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	PlanType findById(Integer id);

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
	PlanType save(PlanType bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	PlanType updateByUpdater(Updater<PlanType> updater);

}
