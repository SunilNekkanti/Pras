package com.pfchoice.core.service;

import com.pfchoice.core.entity.LeadMembershipHospitalizationDetails;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface LeadMembershipHospitalizationDetailsService {

	/**
	 * @param id
	 * @return
	 */
	LeadMembershipHospitalizationDetails deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	LeadMembershipHospitalizationDetails findById(Integer id);

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
	LeadMembershipHospitalizationDetails save(LeadMembershipHospitalizationDetails bean);

	/**
	 * @param bean
	 * @return
	 */
	LeadMembershipHospitalizationDetails update(LeadMembershipHospitalizationDetails bean);

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
