package com.pfchoice.core.service;

import com.pfchoice.core.entity.Membership;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface MembershipService
{

	Membership deleteById(Integer id);

	Membership findById(Integer id);

    Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);
    
    Pagination getPage(int pageNo, int pageSize, String sSearch, int sSearchIns, int sSearchPrvdr, int sSearchHedisCode, String sort, String sortdir);

    Membership save(Membership bean);

    Membership update(Membership bean);
    
}
