package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.LeadMembershipHospitalizationDao;
import com.pfchoice.core.entity.LeadMembershipHospitalization;
import com.pfchoice.core.service.LeadMembershipHospitalizationService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class LeadMembershipHospitalizationServiceImpl implements LeadMembershipHospitalizationService {

	@Autowired
	private LeadMembershipHospitalizationDao leadMembershipHospitalizationDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipHospitalizationService#deleteById(
	 * java.lang.Integer)
	 */
	@Override
	public LeadMembershipHospitalization deleteById(final Integer id) {
		return leadMembershipHospitalizationDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipHospitalizationService#findById(java.
	 * lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public LeadMembershipHospitalization findById(final Integer id) {
		return leadMembershipHospitalizationDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipHospitalizationService#getPage(int,
	 * int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return leadMembershipHospitalizationDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.LeadMembershipHospitalizationService#save(com.
	 * pfchoice.core.entity.LeadMembershipHospitalization)
	 */
	@Override
	public LeadMembershipHospitalization save(final LeadMembershipHospitalization bean) {
		return leadMembershipHospitalizationDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipHospitalizationService#update(com.
	 * pfchoice.core.entity.LeadMembershipHospitalization)
	 */
	@Override
	public LeadMembershipHospitalization update(final LeadMembershipHospitalization bean) {
		Updater<LeadMembershipHospitalization> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return leadMembershipHospitalizationDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipHospitalizationService#update(com.
	 * pfchoice.core.entity.LeadMembershipHospitalization)
	 */
	@Override
	public Integer loadDataCSV2Table(final String fileName) {
		return leadMembershipHospitalizationDao.loadDataCSV2Table(fileName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipHospitalizationService#isDataExists()
	 */
	@Override
	public Boolean isDataExists() {
		return leadMembershipHospitalizationDao.isDataExists();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipHospitalizationService#loadData(java.
	 * lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId) {
		return leadMembershipHospitalizationDao.loadData(fileId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipHospitalizationService#loadData(java.
	 * lang.Integer)
	 */
	@Override
	public Integer unloadCSV2Table() {
		return leadMembershipHospitalizationDao.unloadCSV2Table();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipHospitalizationService#updateData(
	 * java.lang.Integer)
	 */
	@Override
	public Integer updateData(final Integer fileId) {
		return leadMembershipHospitalizationDao.updateData(fileId);
	}
}
