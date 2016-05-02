package com.pfchoice.core.dao;

import com.pfchoice.core.entity.MembershipHospitalization;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface MembershipHospitalizationDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipHospitalization deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	MembershipHospitalization findById(Integer id);

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
	MembershipHospitalization save(MembershipHospitalization bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	MembershipHospitalization updateByUpdater(Updater<MembershipHospitalization> updater);

}
