package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.LeadMembershipInsuranceDao;
import com.pfchoice.core.entity.LeadMembershipInsurance;
import com.pfchoice.core.service.LeadMembershipInsuranceService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class LeadMembershipInsuranceServiceImpl implements LeadMembershipInsuranceService {

	@Autowired
	private LeadMembershipInsuranceDao membershipInsuranceDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipInsuranceService#deleteById(java.lang
	 * .Integer)
	 */
	@Override
	public LeadMembershipInsurance deleteById(final Integer id) {
		return membershipInsuranceDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipInsuranceService#findById(java.lang.
	 * Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public LeadMembershipInsurance findById(final Integer id) {
		return membershipInsuranceDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.LeadMembershipInsuranceService#getPage(int,
	 * int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return membershipInsuranceDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipInsuranceService#save(com.pfchoice.
	 * core.entity.LeadMembershipInsurance)
	 */
	@Override
	public LeadMembershipInsurance save(final LeadMembershipInsurance bean) {
		return membershipInsuranceDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipInsuranceService#update(com.pfchoice.
	 * core.entity.LeadMembershipInsurance)
	 */
	@Override
	public LeadMembershipInsurance update(final LeadMembershipInsurance bean) {
		Updater<LeadMembershipInsurance> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return membershipInsuranceDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipInsuranceService#findAllByMbrId(java.
	 * lang.Integer)
	 */
	@Override
	public List<LeadMembershipInsurance> findAllByMbrId(Integer id) {
		return membershipInsuranceDao.findAllByMbrId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipInsuranceService#findByMbrId(java.
	 * lang.Integer)
	 */
	@Override
	public LeadMembershipInsurance findByMbrId(final Integer id) {
		return membershipInsuranceDao.findByMbrId(id);
	}
}
