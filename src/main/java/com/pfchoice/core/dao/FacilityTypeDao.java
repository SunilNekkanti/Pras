package com.pfchoice.core.dao;

import com.pfchoice.core.entity.FacilityType;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface FacilityTypeDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	FacilityType deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	FacilityType findById(Integer id);

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
	FacilityType save(FacilityType bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	FacilityType updateByUpdater(Updater<FacilityType> updater);

	/**
	 * @param fileId
	 * @return
	 */
	Integer loadData(Integer fileId);
}
