package com.pfchoice.core.service;


import com.pfchoice.core.entity.ClaimType;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface ClaimTypeService {

	/**
	 * @param id
	 * @return
	 */
	ClaimType deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	ClaimType findById(Integer id);

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
	ClaimType save(ClaimType bean);

	/**
	 * @param bean
	 * @return
	 */
	ClaimType update(ClaimType bean);
	

}
