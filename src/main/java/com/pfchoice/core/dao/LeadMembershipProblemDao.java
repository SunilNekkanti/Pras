package com.pfchoice.core.dao;

import com.pfchoice.core.entity.LeadMembership;
import com.pfchoice.core.entity.LeadMembershipProblem;
import com.pfchoice.core.entity.Problem;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface LeadMembershipProblemDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembershipProblem deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembershipProblem findById(Integer id);

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
	LeadMembershipProblem save(LeadMembershipProblem bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	LeadMembershipProblem updateByUpdater(Updater<LeadMembershipProblem> updater);

	/**
	 * @return
	 */
	Integer loadData(Integer fileId, Integer insId, Integer leadMbrId, String insuranceCode, Integer reportMonth);

	/**
	 * @param mbrId
	 * @param pbmId
	 * @param id
	 * @return
	 */
	Integer findByMbrIdAndPbmId(LeadMembership mbr,  Problem pbm,  Integer id) ;
}
