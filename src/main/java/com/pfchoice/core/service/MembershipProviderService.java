package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.MembershipProvider;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface MembershipProviderService {

	/**
	 * @param id
	 * @return
	 */
	MembershipProvider deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	MembershipProvider findById(Integer id);

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
	MembershipProvider save(MembershipProvider bean);

	/**
	 * @param bean
	 * @return
	 */
	MembershipProvider update(MembershipProvider bean);

	/**
	 * @param id
	 * @return
	 */
	List<MembershipProvider> findAllByMbrId(Integer id);

	/**
	 * @param id
	 * @return
	 */
	MembershipProvider findByMbrId(final Integer id);

}
