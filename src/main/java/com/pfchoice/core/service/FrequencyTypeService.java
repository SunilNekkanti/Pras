package com.pfchoice.core.service;

import com.pfchoice.core.entity.FrequencyType;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface FrequencyTypeService {

	/**
	 * @param id
	 * @return
	 */
	FrequencyType deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	FrequencyType findById(Integer id);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

	/**
	 * @param bean
	 * @return
	 */
	FrequencyType save(FrequencyType bean);

	/**
	 * @param bean
	 * @return
	 */
	FrequencyType update(FrequencyType bean);

	/**
	 * @param code
	 * @return
	 */
	FrequencyType findByDescription(String description);
}
