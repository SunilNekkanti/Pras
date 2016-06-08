package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipClaimDetailsDao;
import com.pfchoice.core.entity.MembershipClaimDetails;
import com.pfchoice.core.service.MembershipClaimDetailsService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class MembershipClaimDetailsServiceImpl implements MembershipClaimDetailsService {

	@Autowired
	private MembershipClaimDetailsDao membershipClaimDetailsDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipClaimDetailsService#deleteById(java.
	 * lang.Integer)
	 */
	@Override
	public MembershipClaimDetails deleteById(final Integer id) {
		return membershipClaimDetailsDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipClaimDetailsService#findById(java.
	 * lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public MembershipClaimDetails findById(final Integer id) {
		return membershipClaimDetailsDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimDetailsService#getPage(int,
	 * int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return membershipClaimDetailsDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipClaimDetailsService#save(com.pfchoice
	 * .core.entity.MembershipClaimDetails)
	 */
	@Override
	public MembershipClaimDetails save(final MembershipClaimDetails bean) {
		return membershipClaimDetailsDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimDetailsService#update(com.
	 * pfchoice.core.entity.MembershipClaimDetails)
	 */
	@Override
	public MembershipClaimDetails update(final MembershipClaimDetails bean) {
		Updater<MembershipClaimDetails> updater = new Updater<>(bean);
		return membershipClaimDetailsDao.updateByUpdater(updater);
	}

	@Override
	public Integer loadData(final Integer fileId, final String tableName) {
		return membershipClaimDetailsDao.loadData(fileId, tableName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimDetailsService#
	 * getMbrClaimDetailsPage(int)
	 */
	@Override
	public Pagination getMbrClaimDetailsPage(final int mbrHosId) {
		return membershipClaimDetailsDao.getMbrClaimDetailsPage(mbrHosId);
	}
}
