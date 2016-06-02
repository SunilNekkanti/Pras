package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.HedisMeasureRule;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface HedisMeasureRuleService {

	/**
	 * @param id
	 * @return
	 */
	HedisMeasureRule deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	HedisMeasureRule findById(Integer id);

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
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @param insId
	 * @param effYear
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir, int insId, int effYear);

	/**
	 * @param bean
	 * @return
	 */
	HedisMeasureRule save(HedisMeasureRule bean);

	/**
	 * @param bean
	 * @return
	 */
	HedisMeasureRule update(HedisMeasureRule bean);

	/**
	 * @param insId
	 * @return
	 */
	List<HedisMeasureRule> findAllByInsId(Integer insId);

	/**
	 * @param insId
	 * @return
	 */
	List<HedisMeasureRule> findAllByInsAndPbm(Integer insId);
}
