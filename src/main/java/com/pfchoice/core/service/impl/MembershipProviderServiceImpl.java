package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipProviderDao;
import com.pfchoice.core.entity.MembershipProvider;
import com.pfchoice.core.service.MembershipProviderService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class MembershipProviderServiceImpl implements MembershipProviderService {

	@Autowired
	private MembershipProviderDao membershipProviderDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipProviderService#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public MembershipProvider deleteById(final Integer id) {
		return membershipProviderDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipProviderService#findById(java.lang.
	 * Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public MembershipProvider findById(final Integer id) {
		return membershipProviderDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipProviderService#getPage(int,
	 * int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return membershipProviderDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipProviderService#save(com.pfchoice.
	 * core.entity.MembershipProvider)
	 */
	@Override
	public MembershipProvider save(final MembershipProvider bean) {
		return membershipProviderDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipProviderService#update(com.pfchoice.
	 * core.entity.MembershipProvider)
	 */
	@Override
	public MembershipProvider update(final MembershipProvider bean) {
		Updater<MembershipProvider> updater = new Updater<>(bean);
		return membershipProviderDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipProviderService#findAllByMbrId(java.
	 * lang.Integer)
	 */
	@Override
	public List<MembershipProvider> findAllByMbrId(final Integer id) {
		return membershipProviderDao.findAllByMbrId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipProviderService#findByMbrId(java.lang
	 * .Integer)
	 */
	@Override
	public MembershipProvider findByMbrId(final Integer id) {
		return membershipProviderDao.findByMbrId(id);
	}
}
