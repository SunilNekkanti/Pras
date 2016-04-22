package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.ICDMeasure;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface ICDMeasureService {

	ICDMeasure deleteById(Integer id);

	ICDMeasure findById(Integer id);

	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

	ICDMeasure save(ICDMeasure bean);

	ICDMeasure update(ICDMeasure bean);

	List<ICDMeasure> findAll();

}
