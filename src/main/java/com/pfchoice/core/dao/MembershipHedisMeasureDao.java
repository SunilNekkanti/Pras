package com.pfchoice.core.dao;

import com.pfchoice.core.entity.MembershipHedisMeasure;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface MembershipHedisMeasureDao
{

	MembershipHedisMeasure deleteById(Integer id);

	MembershipHedisMeasure findById(Integer id);

    Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

    Pagination getPage(int pageNo, int pageSize, String sSearch, Integer sSearchIns, 
    		Integer sSearchPrvdr, Integer sSearchHedisCode, String sort, String sortdir);
    
    MembershipHedisMeasure save(MembershipHedisMeasure bean);

    MembershipHedisMeasure updateByUpdater(Updater<MembershipHedisMeasure> updater);
    
}
