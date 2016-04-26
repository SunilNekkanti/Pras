package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.HedisMeasureRule;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface HedisMeasureRuleDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	HedisMeasureRule deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	HedisMeasureRule findById(Integer id);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @param insId
	 * @param effYear
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir, Integer insId,
			Integer effYear);

	/**
	 * 
	 * @param bean
	 * @return
	 */
	HedisMeasureRule save(HedisMeasureRule bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	HedisMeasureRule updateByUpdater(Updater<HedisMeasureRule> updater);

	/**
	 * 
	 * @param insId
	 * @return
	 */
	List<HedisMeasureRule> findAllByInsId(Integer insId);

}
