package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.Contract;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface ContractDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	Contract deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Contract findById(Integer id);

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
	Contract save(Contract bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	Contract updateByUpdater(Updater<Contract> updater);

	/**
	 * 
	 * @param refString
	 * @param id
	 * @return
	 */
	List<Contract> findAllContractsByRefId(String refString, Integer id);

	/**
	 * 
	 * @param refString
	 * @param id
	 * @return
	 */
	Contract findActiveContractByRefId(String refString, Integer id);

}
