package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.Contract;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface ContractDao
{

	Contract deleteById(Integer id);

	Contract findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    Contract save(Contract bean);

    Contract updateByUpdater(Updater<Contract> updater);
    
    List<Contract> findAllContractsByRefId(String refString, Integer id);
    
    Contract findActiveContractByRefId(String refString, Integer id);

}
