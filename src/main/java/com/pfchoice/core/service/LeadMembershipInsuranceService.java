package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.LeadMembershipInsurance;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface LeadMembershipInsuranceService {

	/**
	 * @param id
	 * @return
	 */
	LeadMembershipInsurance deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	LeadMembershipInsurance findById(Integer id);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * @param bean
	 * @return
	 */
	LeadMembershipInsurance save(LeadMembershipInsurance bean);

	/**
	 * @param bean
	 * @return
	 */
	LeadMembershipInsurance update(LeadMembershipInsurance bean);

	/**
	 * @param id
	 * @return
	 */
	List<LeadMembershipInsurance> findAllByMbrId(Integer id);

	/**
	 * @param id
	 * @return
	 */
	LeadMembershipInsurance findByMbrId(final Integer id);

}
