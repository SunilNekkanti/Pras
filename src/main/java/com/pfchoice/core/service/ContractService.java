package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.Contract;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface ContractService {

	Contract deleteById(Integer id);

	Contract findById(Integer id);

	Pagination getPage(int pageNo, int pageSize);

	Contract save(Contract bean);

	Contract update(Contract bean);

	List<Contract> findAllContractsByRefId(String refString, Integer id);

	Contract findActiveContractByRefId(String refString, Integer id);

}
