package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipStatusDao;
import com.pfchoice.core.entity.MembershipStatus;
import com.pfchoice.core.service.MembershipStatusService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class MembershipStatusServiceImpl implements MembershipStatusService {

	@Autowired
	private MembershipStatusDao membershipStatusDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipStatusService#deleteById(java.lang.
	 * Byte)
	 */
	@Override
	public MembershipStatus deleteById(final Byte id) {
		return membershipStatusDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipStatusService#findById(java.lang.
	 * Byte)
	 */
	@Override
	@Transactional(readOnly = true)
	public MembershipStatus findById(final Byte id) {
		return membershipStatusDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipStatusService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return membershipStatusDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipStatusService#save(com.pfchoice.core.
	 * entity.MembershipStatus)
	 */
	@Override
	public MembershipStatus save(final MembershipStatus bean) {
		return membershipStatusDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipStatusService#update(com.pfchoice.
	 * core.entity.MembershipStatus)
	 */
	@Override
	public MembershipStatus update(final MembershipStatus bean) {
		Updater<MembershipStatus> updater = new Updater<>(bean);
		return membershipStatusDao.updateByUpdater(updater);
	}

}
