package com.pfchoice.core.dao;

import java.util.Date;
import java.util.List;

import com.pfchoice.core.entity.Membership;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface MembershipDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	Membership deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Membership findById(Integer id);

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
	 * @param sSearchIns
	 * @param sSearchPrvdr
	 * @param sSearchHedisCode
	 * @param ruleIds
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, Integer sSearchIns, Integer sSearchPrvdr,
			Integer sSearchHedisCode, final List<Integer> ruleIds, String sort, String sortdir);
	
	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sSearchIns
	 * @param sSearchPrvdr
	 * @param sort
	 * @param sortdir
	 * @param processingFrom
	 * @param processingTo
	 * @param processHospitalization
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, Integer sSearchIns, Integer sSearchPrvdr, String sort, String sortdir, Date processingFrom, Date processingTo, Integer processHospitalization);

	/**
	 * 
	 * @param bean
	 * @return
	 */
	Membership save(Membership bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	Membership updateByUpdater(Updater<Membership> updater);

}
