package com.pfchoice.core.service;

import com.pfchoice.core.entity.HedisMeasureGroup;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface HedisMeasureGroupService {

	/**
	 * @param id
	 * @return
	 */
	HedisMeasureGroup deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	HedisMeasureGroup findById(Integer id);

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
	HedisMeasureGroup save(HedisMeasureGroup bean);

	/**
	 * @param bean
	 * @return
	 */
	HedisMeasureGroup update(HedisMeasureGroup bean);

}
