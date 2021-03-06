package com.pfchoice.core.dao;

import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.entity.MembershipProblem;
import com.pfchoice.core.entity.Problem;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface MembershipProblemDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipProblem deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipProblem findById(Integer id);

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
	MembershipProblem save(MembershipProblem bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	MembershipProblem updateByUpdater(Updater<MembershipProblem> updater);

	/**
	 * @return
	 */
	Integer loadData(Integer fileId, Integer insId, String insuranceCode, Integer reportMonth);

	/**
	 * @param mbrId
	 * @param pbmId
	 * @param id
	 * @return
	 */
	Integer findByMbrIdAndPbmId(Membership mbr,  Problem pbm,  Integer id) ;
}
