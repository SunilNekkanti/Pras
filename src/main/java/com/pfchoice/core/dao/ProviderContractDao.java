package com.pfchoice.core.dao;

import java.util.List;
import com.pfchoice.core.entity.ProviderContract;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface ProviderContractDao
{

	ProviderContract deleteById(Integer id);

	ProviderContract findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    ProviderContract save(ProviderContract bean);

    ProviderContract updateByUpdater(Updater<ProviderContract> updater);
    
    List<ProviderContract> findAll();

}
