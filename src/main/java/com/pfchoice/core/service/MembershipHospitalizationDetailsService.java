package com.pfchoice.core.service;

import com.pfchoice.core.entity.MembershipHospitalizationDetails;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface MembershipHospitalizationDetailsService {

	/**
	 * @param id
	 * @return
	 */
	MembershipHospitalizationDetails deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	MembershipHospitalizationDetails findById(Integer id);

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
	MembershipHospitalizationDetails save(MembershipHospitalizationDetails bean);

	/**
	 * @param bean
	 * @return
	 */
	MembershipHospitalizationDetails update(MembershipHospitalizationDetails bean);

	/**
	 * @param fileId
	 * @return
	 */
	Integer loadData(Integer fileId);
	
	/**
	 * @param mbrHosId
	 * @return
	 */
	Pagination getMbrHospitalizationDetailsPage(int mbrHosId);
}
