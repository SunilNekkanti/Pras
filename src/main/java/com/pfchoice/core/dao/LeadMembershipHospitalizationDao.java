package com.pfchoice.core.dao;

import com.pfchoice.core.entity.LeadMembershipHospitalization;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface LeadMembershipHospitalizationDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembershipHospitalization deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	LeadMembershipHospitalization findById(Integer id);

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
	LeadMembershipHospitalization save(LeadMembershipHospitalization bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	LeadMembershipHospitalization updateByUpdater(Updater<LeadMembershipHospitalization> updater);

	/**
	 * @return
	 */
	Integer loadDataCSV2Table(String fileName);

	/**
	 * @return
	 */
	Integer loadData(Integer fileId);

	/**
	 * @return
	 */
	Boolean isDataExists();

	/**
	 * @return
	 */
	Integer unloadCSV2Table();

	/**
	 * @return
	 */
	Integer updateData(Integer fileId);

}
