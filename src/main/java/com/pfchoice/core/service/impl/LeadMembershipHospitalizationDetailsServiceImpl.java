package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.LeadMembershipHospitalizationDetailsDao;
import com.pfchoice.core.entity.LeadMembershipHospitalizationDetails;
import com.pfchoice.core.service.LeadMembershipHospitalizationDetailsService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class LeadMembershipHospitalizationDetailsServiceImpl implements LeadMembershipHospitalizationDetailsService {

	@Autowired
	private LeadMembershipHospitalizationDetailsDao leadMembershipHospitalizationDetailsDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.LeadMembershipHospitalizationDetailsService#
	 * deleteById(java.lang.Integer)
	 */
	@Override
	public LeadMembershipHospitalizationDetails deleteById(final Integer id) {
		return leadMembershipHospitalizationDetailsDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.LeadMembershipHospitalizationDetailsService#
	 * findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public LeadMembershipHospitalizationDetails findById(final Integer id) {
		return leadMembershipHospitalizationDetailsDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipHospitalizationDetailsService#getPage
	 * (int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return leadMembershipHospitalizationDetailsDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipHospitalizationDetailsService#save(
	 * com.pfchoice.core.entity.LeadMembershipHospitalizationDetails)
	 */
	@Override
	public LeadMembershipHospitalizationDetails save(final LeadMembershipHospitalizationDetails bean) {
		return leadMembershipHospitalizationDetailsDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipHospitalizationDetailsService#update(
	 * com.pfchoice.core.entity.LeadMembershipHospitalizationDetails)
	 */
	@Override
	public LeadMembershipHospitalizationDetails update(final LeadMembershipHospitalizationDetails bean) {
		Updater<LeadMembershipHospitalizationDetails> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return leadMembershipHospitalizationDetailsDao.updateByUpdater(updater);
	}

	@Override
	public Integer loadData(final Integer fileId) {
		return leadMembershipHospitalizationDetailsDao.loadData(fileId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.LeadMembershipHospitalizationDetailsService#
	 * getMbrHospitalizationDetailsPage(int)
	 */
	@Override
	public Pagination getMbrHospitalizationDetailsPage(final int mbrHosId) {
		return leadMembershipHospitalizationDetailsDao.getMbrHospitalizationDetailsPage(mbrHosId);
	}
}
