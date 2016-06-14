package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipActivityMonthDao;
import com.pfchoice.core.entity.MembershipActivityMonth;
import com.pfchoice.core.service.MembershipActivityMonthService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class MembershipActivityMonthServiceImpl implements MembershipActivityMonthService {

	@Autowired
	private MembershipActivityMonthDao membershipActivityMonthDao;

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipActivityMonthService#deleteById(java.lang.Integer)
	 */
	@Override
	public MembershipActivityMonth deleteById(final Integer id) {
		return membershipActivityMonthDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipActivityMonthService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public MembershipActivityMonth findById(final Integer id) {
		return membershipActivityMonthDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipActivityMonthService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return membershipActivityMonthDao.getPage(pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipActivityMonthService#save(com.pfchoice.core.entity.MembershipActivityMonth)
	 */
	@Override
	public MembershipActivityMonth save(final MembershipActivityMonth bean) {
		return membershipActivityMonthDao.save(bean);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipActivityMonthService#update(com.pfchoice.core.entity.MembershipActivityMonth)
	 */
	@Override
	public MembershipActivityMonth update(final MembershipActivityMonth bean) {
		Updater<MembershipActivityMonth> updater = new Updater<>(bean);
		return membershipActivityMonthDao.updateByUpdater(updater);
	}

}
