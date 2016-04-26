package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.Contract;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface ContractService {

	/**
	 * @param id
	 * @return
	 */
	Contract deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	Contract findById(Integer id);

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
	Contract save(Contract bean);

	/**
	 * @param bean
	 * @return
	 */
	Contract update(Contract bean);

	/**
	 * @param refString
	 * @param id
	 * @return
	 */
	List<Contract> findAllContractsByRefId(String refString, Integer id);

	/**
	 * @param refString
	 * @param id
	 * @return
	 */
	Contract findActiveContractByRefId(String refString, Integer id);

}
