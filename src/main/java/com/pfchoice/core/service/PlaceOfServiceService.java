package com.pfchoice.core.service;

import com.pfchoice.core.entity.PlaceOfService;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface PlaceOfServiceService {

	/**
	 * @param id
	 * @return
	 */
	PlaceOfService deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	PlaceOfService findById(Integer id);

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
	PlaceOfService save(PlaceOfService bean);

	/**
	 * @param bean
	 * @return
	 */
	PlaceOfService update(PlaceOfService bean);

}
