package com.pfchoice.core.service;

import com.pfchoice.core.entity.FacilityType;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface FacilityTypeService {

	/**
	 * @param id
	 * @return
	 */
	FacilityType deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	FacilityType findById(Integer id);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * @param bean
	 * @return
	 */
	FacilityType save(FacilityType bean);

	/**
	 * @param bean
	 * @return
	 */
	FacilityType update(FacilityType bean);

	/**
	 * @param fileId
	 * @return
	 */
	Integer loadData(Integer fileId);
}
