package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.CPTMeasure;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface CPTMeasureService {

	/**
	 * @param id
	 * @return
	 */
	CPTMeasure deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	CPTMeasure findById(Integer id);

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
	CPTMeasure save(CPTMeasure bean);

	/**
	 * @param bean
	 * @return
	 */
	CPTMeasure update(CPTMeasure bean);

	/**
	 * @return
	 */
	List<CPTMeasure> findAll();

}
