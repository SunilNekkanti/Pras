package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipHedisFollowupDao;
import com.pfchoice.core.entity.MembershipHedisFollowup;
import com.pfchoice.core.service.MembershipHedisFollowupService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class MembershipHedisFollowupServiceImpl implements MembershipHedisFollowupService {

	@Autowired
	private MembershipHedisFollowupDao mbrHedisFollowupDao;

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipHedisFollowupService#deleteById(java.lang.Integer)
	 */
	@Override
	public MembershipHedisFollowup deleteById(final Integer id) {
		return mbrHedisFollowupDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipHedisFollowupService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public MembershipHedisFollowup findById(final Integer id) {
		return mbrHedisFollowupDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipHedisFollowupService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return mbrHedisFollowupDao.getPage(pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipHedisFollowupService#save(com.pfchoice.core.entity.MembershipHedisFollowup)
	 */
	@Override
	public MembershipHedisFollowup save(final MembershipHedisFollowup bean) {
		return mbrHedisFollowupDao.save(bean);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipHedisFollowupService#update(com.pfchoice.core.entity.MembershipHedisFollowup)
	 */
	@Override
	public MembershipHedisFollowup update(final MembershipHedisFollowup bean) {
		Updater<MembershipHedisFollowup> updater = new Updater<>(bean);
		return mbrHedisFollowupDao.updateByUpdater(updater);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipHedisFollowupService#findAllByMbrId(java.lang.Integer)
	 */
	@Override
	public List<MembershipHedisFollowup> findAllByMbrId(final Integer id) {
		return mbrHedisFollowupDao.findAllByMbrId(id);
	}
}
