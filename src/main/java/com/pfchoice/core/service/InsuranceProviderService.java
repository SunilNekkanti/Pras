package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.InsuranceProvider;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface InsuranceProviderService
{

	InsuranceProvider deleteById(Integer id);

	InsuranceProvider findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    InsuranceProvider save(InsuranceProvider bean);

    InsuranceProvider update(InsuranceProvider bean);
    
    List<InsuranceProvider> findAll();
    
    List<InsuranceProvider> findAllByPrvdrId(Integer id);
    

}
