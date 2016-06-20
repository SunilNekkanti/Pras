package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipFollowupDao;
import com.pfchoice.core.entity.MembershipFollowup;
import com.pfchoice.core.service.MembershipFollowupService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class MembershipFollowupServiceImpl implements MembershipFollowupService {

	@Autowired
	private MembershipFollowupDao mbrHedisFollowupDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipHedisFollowupService#deleteById(java.
	 * lang.Integer)
	 */
	@Override
	public MembershipFollowup deleteById(final Integer id) {
		return mbrHedisFollowupDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipHedisFollowupService#findById(java.
	 * lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public MembershipFollowup findById(final Integer id) {
		return mbrHedisFollowupDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipHedisFollowupService#getPage(int,
	 * int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return mbrHedisFollowupDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipHedisFollowupService#save(com.
	 * pfchoice.core.entity.MembershipHedisFollowup)
	 */
	@Override
	public MembershipFollowup save(final MembershipFollowup bean) {
		return mbrHedisFollowupDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipHedisFollowupService#update(com.
	 * pfchoice.core.entity.MembershipHedisFollowup)
	 */
	@Override
	public MembershipFollowup update(final MembershipFollowup bean) {
		Updater<MembershipFollowup> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return mbrHedisFollowupDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipHedisFollowupService#findAllByMbrId(
	 * java.lang.Integer)
	 */
	@Override
	public List<MembershipFollowup> findAllByMbrId(final Integer id, final String followupTypeCode) {
		return mbrHedisFollowupDao.findAllByMbrId(id, followupTypeCode);
	}
}
