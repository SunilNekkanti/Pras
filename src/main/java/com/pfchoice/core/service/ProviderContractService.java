package com.pfchoice.core.service;

import java.util.List;
import com.pfchoice.core.entity.ProviderContract;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface ProviderContractService
{

	ProviderContract deleteById(Integer id);

	ProviderContract findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    ProviderContract save(ProviderContract bean);

    ProviderContract update(ProviderContract bean);
    
    List<ProviderContract> findAll();

}
