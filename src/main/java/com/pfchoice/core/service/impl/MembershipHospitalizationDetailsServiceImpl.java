package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipHospitalizationDetailsDao;
import com.pfchoice.core.entity.MembershipHospitalizationDetails;
import com.pfchoice.core.service.MembershipHospitalizationDetailsService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class MembershipHospitalizationDetailsServiceImpl implements MembershipHospitalizationDetailsService {

	@Autowired
	private MembershipHospitalizationDetailsDao membershipHospitalizationDetailsDao;

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipHospitalizationDetailsService#deleteById(java.lang.Integer)
	 */
	@Override
	public MembershipHospitalizationDetails deleteById(final Integer id) {
		return membershipHospitalizationDetailsDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipHospitalizationDetailsService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public MembershipHospitalizationDetails findById(final Integer id) {
		return membershipHospitalizationDetailsDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipHospitalizationDetailsService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return membershipHospitalizationDetailsDao.getPage(pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipHospitalizationDetailsService#save(com.pfchoice.core.entity.MembershipHospitalizationDetails)
	 */
	@Override
	public MembershipHospitalizationDetails save(final MembershipHospitalizationDetails bean) {
		return membershipHospitalizationDetailsDao.save(bean);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipHospitalizationDetailsService#update(com.pfchoice.core.entity.MembershipHospitalizationDetails)
	 */
	@Override
	public MembershipHospitalizationDetails update(final MembershipHospitalizationDetails bean) {
		Updater<MembershipHospitalizationDetails> updater = new Updater<>(bean);
		return membershipHospitalizationDetailsDao.updateByUpdater(updater);
	}

	@Override
	public Integer loadData(final Integer fileId){
		return membershipHospitalizationDetailsDao.loadData(fileId);
	}
}
