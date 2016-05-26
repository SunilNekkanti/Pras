package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.Problem;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface ProblemDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	Problem deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Problem findById(Integer id);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

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
	 * @param bean
	 * @return
	 */
	Problem save(Problem bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	Problem updateByUpdater(Updater<Problem> updater);
	
	
}
