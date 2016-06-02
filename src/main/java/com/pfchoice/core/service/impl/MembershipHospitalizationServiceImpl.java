package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipHospitalizationDao;
import com.pfchoice.core.entity.MembershipHospitalization;
import com.pfchoice.core.service.MembershipHospitalizationService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class MembershipHospitalizationServiceImpl implements MembershipHospitalizationService {

	@Autowired
	private MembershipHospitalizationDao membershipHospitalizationDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipHospitalizationService#deleteById(
	 * java.lang.Integer)
	 */
	@Override
	public MembershipHospitalization deleteById(final Integer id) {
		return membershipHospitalizationDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipHospitalizationService#findById(java.
	 * lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public MembershipHospitalization findById(final Integer id) {
		return membershipHospitalizationDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipHospitalizationService#getPage(int,
	 * int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return membershipHospitalizationDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipHospitalizationService#save(com.
	 * pfchoice.core.entity.MembershipHospitalization)
	 */
	@Override
	public MembershipHospitalization save(final MembershipHospitalization bean) {
		return membershipHospitalizationDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipHospitalizationService#update(com.
	 * pfchoice.core.entity.MembershipHospitalization)
	 */
	@Override
	public MembershipHospitalization update(final MembershipHospitalization bean) {
		Updater<MembershipHospitalization> updater = new Updater<>(bean);
		return membershipHospitalizationDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipHospitalizationService#update(com.
	 * pfchoice.core.entity.MembershipHospitalization)
	 */
	@Override
	public Integer loadDataCSV2Table(final String fileName) {
		return membershipHospitalizationDao.loadDataCSV2Table(fileName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipHospitalizationService#isDataExists()
	 */
	@Override
	public Boolean isDataExists() {
		return membershipHospitalizationDao.isDataExists();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipHospitalizationService#loadData(java.
	 * lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId) {
		return membershipHospitalizationDao.loadData(fileId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipHospitalizationService#loadData(java.
	 * lang.Integer)
	 */
	@Override
	public Integer unloadCSV2Table() {
		return membershipHospitalizationDao.unloadCSV2Table();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipHospitalizationService#updateData(
	 * java.lang.Integer)
	 */
	@Override
	public Integer updateData(final Integer fileId) {
		return membershipHospitalizationDao.updateData(fileId);
	}
}
