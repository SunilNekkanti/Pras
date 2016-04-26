package com.pfchoice.core.service;

import com.pfchoice.core.entity.Gender;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface GenderService {

	/**
	 * @param id
	 * @return
	 */
	Gender deleteById(Byte id);

	/**
	 * @param id
	 * @return
	 */
	Gender findById(Byte id);

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
	Gender save(Gender bean);

	/**
	 * @param bean
	 * @return
	 */
	Gender update(Gender bean);

}
