package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.MembershipInsurance;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface MembershipInsuranceService {

	/**
	 * @param id
	 * @return
	 */
	MembershipInsurance deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	MembershipInsurance findById(Integer id);

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
	MembershipInsurance save(MembershipInsurance bean);

	/**
	 * @param bean
	 * @return
	 */
	MembershipInsurance update(MembershipInsurance bean);

	/**
	 * @param id
	 * @return
	 */
	List<MembershipInsurance> findAllByMbrId(Integer id);

	/**
	 * @param id
	 * @return
	 */
	MembershipInsurance findByMbrId(final Integer id);

}
