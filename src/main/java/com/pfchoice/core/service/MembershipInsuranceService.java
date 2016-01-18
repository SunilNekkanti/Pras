package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.MembershipInsurance;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface MembershipInsuranceService
{

	MembershipInsurance deleteById(Integer id);

	MembershipInsurance findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    MembershipInsurance save(MembershipInsurance bean);

    MembershipInsurance update(MembershipInsurance bean);
    
    List<MembershipInsurance> findAll();

}
