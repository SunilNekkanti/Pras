package com.pfchoice.core.dao;

import java.util.List;
import com.pfchoice.core.entity.InsuranceContract;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface InsuranceContractDao
{

	InsuranceContract deleteById(Integer id);

	InsuranceContract findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    InsuranceContract save(InsuranceContract bean);

    InsuranceContract updateByUpdater(Updater<InsuranceContract> updater);
    
    List<InsuranceContract> findAll();

}
