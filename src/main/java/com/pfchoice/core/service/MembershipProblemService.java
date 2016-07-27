package com.pfchoice.core.service;

import com.pfchoice.core.entity.MembershipProblem;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface MembershipProblemService {

	/**
	 * @param id
	 * @return
	 */
	MembershipProblem deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	MembershipProblem findById(Integer id);

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
	MembershipProblem save(MembershipProblem bean);

	/**
	 * @param bean
	 * @return
	 */
	MembershipProblem update(MembershipProblem bean);

	/**
	 * @return
	 */
	Integer loadData(Integer fileId, Integer insId, String tableName);

}
