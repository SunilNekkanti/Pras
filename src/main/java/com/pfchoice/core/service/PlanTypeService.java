package com.pfchoice.core.service;

import com.pfchoice.core.entity.PlanType;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface PlanTypeService {

	/**
	 * @param id
	 * @return
	 */
	PlanType deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	PlanType findById(Integer id);

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
	PlanType save(PlanType bean);

	/**
	 * @param bean
	 * @return
	 */
	PlanType update(PlanType bean);

}
