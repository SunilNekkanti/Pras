package com.pfchoice.core.dao;

import com.pfchoice.core.entity.MembershipHospitalizationDetails;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface MembershipHospitalizationDetailsDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipHospitalizationDetails deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipHospitalizationDetails findById(Integer id);

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
	MembershipHospitalizationDetails save(MembershipHospitalizationDetails bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	MembershipHospitalizationDetails updateByUpdater(Updater<MembershipHospitalizationDetails> updater);

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
