package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.ZipCode;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface ZipCodeDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	ZipCode deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	ZipCode findById(Integer id);

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
	ZipCode save(ZipCode bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	ZipCode updateByUpdater(Updater<ZipCode> updater);

	/**
	 * 
	 * @param stateCode
	 * @return
	 */
	List<ZipCode> findByStateCode(Integer stateCode);
}
