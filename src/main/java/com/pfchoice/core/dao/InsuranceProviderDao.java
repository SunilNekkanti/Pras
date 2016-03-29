package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.InsuranceProvider;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface InsuranceProviderDao
{

	InsuranceProvider deleteById(Integer id);

	InsuranceProvider findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    InsuranceProvider save(InsuranceProvider bean);

    InsuranceProvider updateByUpdater(Updater<InsuranceProvider> updater);
    
    List<InsuranceProvider> findAll();
    
    List<InsuranceProvider> findAllByPrvdrId(Integer id);
    
}
