package com.pfchoice.core.service;

import com.pfchoice.core.entity.MembershipHedisMeasure;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface MembershipHedisMeasureService
{

	MembershipHedisMeasure deleteById(Integer id);

	MembershipHedisMeasure findById(Integer id);

    Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);
    
    Pagination getPage(int pageNo, int pageSize, String sSearch, int sSearchIns, int sSearchPrvdr, int sSearchHedisCode, String sort, String sortdir);

    MembershipHedisMeasure save(MembershipHedisMeasure bean);

    MembershipHedisMeasure update(MembershipHedisMeasure bean);
    
}
