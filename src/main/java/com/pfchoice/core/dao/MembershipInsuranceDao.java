package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.MembershipInsurance;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface MembershipInsuranceDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipInsurance deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipInsurance findById(Integer id);

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
	MembershipInsurance save(MembershipInsurance bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	MembershipInsurance updateByUpdater(Updater<MembershipInsurance> updater);

	/**
	 * 
	 * @param id
	 * @return
	 */
	List<MembershipInsurance> findAllByMbrId(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipInsurance findByMbrId(Integer id);

}
