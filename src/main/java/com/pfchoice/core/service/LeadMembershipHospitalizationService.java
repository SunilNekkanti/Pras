package com.pfchoice.core.service;

import com.pfchoice.core.entity.LeadMembershipHospitalization;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface LeadMembershipHospitalizationService {

	/**
	 * @param id
	 * @return
	 */
	LeadMembershipHospitalization deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	LeadMembershipHospitalization findById(Integer id);

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
	LeadMembershipHospitalization save(LeadMembershipHospitalization bean);

	/**
	 * @param bean
	 * @return
	 */
	LeadMembershipHospitalization update(LeadMembershipHospitalization bean);

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
	Integer loadDataCSV2Table(String fileName);

	/**
	 * @return
	 */
	Integer unloadCSV2Table();

	/**
	 * @return
	 */
	Integer updateData(Integer fileId);

}
