package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.Membership;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface MembershipDao {

	Membership deleteById(Integer id);

	Membership findById(Integer id);

	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

	Pagination getPage(int pageNo, int pageSize, String sSearch, Integer sSearchIns, Integer sSearchPrvdr,
			Integer sSearchHedisCode, final List<Integer> ruleIds, String sort, String sortdir);

	Membership save(Membership bean);

	Membership updateByUpdater(Updater<Membership> updater);

}
