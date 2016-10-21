package com.pfchoice.core.dao;


import com.pfchoice.core.entity.ClaimType;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface ClaimTypeDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	ClaimType deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	ClaimType findById(Integer id);

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
	ClaimType save(ClaimType bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	ClaimType updateByUpdater(Updater<ClaimType> updater);
	



}
