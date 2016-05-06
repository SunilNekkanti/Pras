package com.pfchoice.core.service;

import com.pfchoice.core.entity.MembershipHospitalization;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface MembershipHospitalizationService {

	/**
	 * @param id
	 * @return
	 */
	MembershipHospitalization deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	MembershipHospitalization findById(Integer id);

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
	MembershipHospitalization save(MembershipHospitalization bean);

	/**
	 * @param bean
	 * @return
	 */
	MembershipHospitalization update(MembershipHospitalization bean);

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
	Integer loadDataCSV2Table();
	
	/**
	 * @return
	 */
	Integer unloadCSV2Table();
}
