package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.LeadMembershipInsurance;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface LeadMembershipInsuranceDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembershipInsurance deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembershipInsurance findById(Integer id);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * 
	 * @param bean
	 * @return
	 */
	LeadMembershipInsurance save(LeadMembershipInsurance bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	LeadMembershipInsurance updateByUpdater(Updater<LeadMembershipInsurance> updater);

	/**
	 * 
	 * @param id
	 * @return
	 */
	List<LeadMembershipInsurance> findAllByMbrId(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembershipInsurance findByMbrId(Integer id);

}
