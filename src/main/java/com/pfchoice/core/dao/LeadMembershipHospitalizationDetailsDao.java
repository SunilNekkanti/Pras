package com.pfchoice.core.dao;

import com.pfchoice.core.entity.LeadMembershipHospitalizationDetails;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface LeadMembershipHospitalizationDetailsDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembershipHospitalizationDetails deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembershipHospitalizationDetails findById(Integer id);

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
	LeadMembershipHospitalizationDetails save(LeadMembershipHospitalizationDetails bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	LeadMembershipHospitalizationDetails updateByUpdater(Updater<LeadMembershipHospitalizationDetails> updater);

	/**
	 * @param fileId
	 * @return
	 */
	Integer loadData(Integer fileId);

	/**
	 * @param mbrHosId
	 * @return
	 */
	Pagination getMbrHospitalizationDetailsPage(Integer mbrHosId);
}
