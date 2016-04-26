package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipInsuranceDao;
import com.pfchoice.core.entity.MembershipInsurance;
import com.pfchoice.core.service.MembershipInsuranceService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class MembershipInsuranceServiceImpl implements MembershipInsuranceService {

	@Autowired
	private MembershipInsuranceDao membershipInsuranceDao;

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipInsuranceService#deleteById(java.lang.Integer)
	 */
	@Override
	public MembershipInsurance deleteById(final Integer id) {
		return membershipInsuranceDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipInsuranceService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public MembershipInsurance findById(final Integer id) {
		return membershipInsuranceDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipInsuranceService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return membershipInsuranceDao.getPage(pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipInsuranceService#save(com.pfchoice.core.entity.MembershipInsurance)
	 */
	@Override
	public MembershipInsurance save(final MembershipInsurance bean) {
		return membershipInsuranceDao.save(bean);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipInsuranceService#update(com.pfchoice.core.entity.MembershipInsurance)
	 */
	@Override
	public MembershipInsurance update(final MembershipInsurance bean) {
		Updater<MembershipInsurance> updater = new Updater<>(bean);
		return membershipInsuranceDao.updateByUpdater(updater);
	}


	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipInsuranceService#findAllByMbrId(java.lang.Integer)
	 */
	@Override
	public List<MembershipInsurance> findAllByMbrId(Integer id) {
		return membershipInsuranceDao.findAllByMbrId(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipInsuranceService#findByMbrId(java.lang.Integer)
	 */
	@Override
	public MembershipInsurance findByMbrId(final Integer id) {
		return membershipInsuranceDao.findByMbrId(id);
	}
}
