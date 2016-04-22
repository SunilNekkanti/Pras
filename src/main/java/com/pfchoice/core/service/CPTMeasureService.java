package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.CPTMeasure;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface CPTMeasureService {

	CPTMeasure deleteById(Integer id);

	CPTMeasure findById(Integer id);

	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

	CPTMeasure save(CPTMeasure bean);

	CPTMeasure update(CPTMeasure bean);

	List<CPTMeasure> findAll();

}
