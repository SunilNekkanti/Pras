package com.pfchoice.core.dao;

import com.pfchoice.core.entity.HedisMeasureGroup;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface HedisMeasureGroupDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	HedisMeasureGroup deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	HedisMeasureGroup findById(Integer id);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * 
	 * @param bean
	 * @return
	 */
	HedisMeasureGroup save(HedisMeasureGroup bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	HedisMeasureGroup updateByUpdater(Updater<HedisMeasureGroup> updater);

}
