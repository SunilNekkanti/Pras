package com.pfchoice.core.service;

import java.util.List;
import com.pfchoice.core.entity.InsuranceContract;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface InsuranceContractService
{

	InsuranceContract deleteById(Integer id);

	InsuranceContract findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    InsuranceContract save(InsuranceContract bean);

    InsuranceContract update(InsuranceContract bean);
    
    List<InsuranceContract> findAll();

}
