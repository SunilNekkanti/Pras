package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipDao;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.service.MembershipService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class MembershipServiceImpl implements MembershipService {

	@Autowired
	private MembershipDao membershipDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipService#deleteById(java.lang.Integer)
	 */
	@Override
	public Membership deleteById(final Integer id) {
		return membershipDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public Membership findById(final Integer id) {
		return membershipDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return membershipDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipService#getPage(int, int,
	 * java.lang.String, int, int, int, java.util.List, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final int sSearchIns,
			final int sSearchPrvdr, final int sSearchHedisCode, final List<Integer> ruleIds, final String sort,
			final String sortdir) {
		return membershipDao.getPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr, sSearchHedisCode, ruleIds,
				sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipService#save(com.pfchoice.core.entity
	 * .Membership)
	 */
	@Override
	public Membership save(final Membership bean) {
		return membershipDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipService#update(com.pfchoice.core.
	 * entity.Membership)
	 */
	@Override
	public Membership update(final Membership bean) {
		Updater<Membership> updater = new Updater<>(bean);
		return membershipDao.updateByUpdater(updater);
	}

}
