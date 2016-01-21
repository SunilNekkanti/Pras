package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.MembershipInsurance;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface MembershipInsuranceDao
{

	MembershipInsurance deleteById(Integer id);

	MembershipInsurance findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    MembershipInsurance save(MembershipInsurance bean);

    MembershipInsurance updateByUpdater(Updater<MembershipInsurance> updater);
    
    List<MembershipInsurance> findAll();
    
    List<MembershipInsurance> findAllByMbrId(Integer id);

}
