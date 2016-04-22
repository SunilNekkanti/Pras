package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.PlanType;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface PlanTypeService {

	PlanType deleteById(Integer id);

	PlanType findById(Integer id);

	Pagination getPage(int pageNo, int pageSize);

	PlanType save(PlanType bean);

	PlanType update(PlanType bean);

	List<PlanType> findAll();

}
