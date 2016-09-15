package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.PharmacyDao;
import com.pfchoice.core.entity.Pharmacy;
import com.pfchoice.core.service.PharmacyService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class PharmacyServiceImpl implements PharmacyService {

	@Autowired
	private PharmacyDao pharmacyDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.AttPhysicianService#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public Pharmacy deleteById(final Integer id) {
		return pharmacyDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.AttPhysicianService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pharmacy findById(final Integer id) {
		return pharmacyDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.AttPhysicianService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return pharmacyDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.AttPhysicianService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return pharmacyDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.AttPhysicianService#save(com.pfchoice.core.
	 * entity.AttPhysician)
	 */
	@Override
	public Pharmacy save(final Pharmacy bean) {
		return pharmacyDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.AttPhysicianService#update(com.pfchoice.core.
	 * entity.AttPhysician)
	 */
	@Override
	public Pharmacy update(final Pharmacy bean) {
		Updater<Pharmacy> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return pharmacyDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.AttPhysicianService#loadData(java.lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId) {
		return pharmacyDao.loadData(fileId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.AttPhysicianService#isNameUnique(java.lang.
	 * Integer, java.lang.String)
	 */
	@Override
	public boolean isCodeUnique(Integer id, String code) {
		Pharmacy pharmacy = findByCode(code);
		return (pharmacy == null || ((id != null) && (pharmacy.getId() == id)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.AttPhysicianService#findByName(java.lang.
	 * String)
	 */
	@Override
	public Pharmacy findByCode(String code) {
		Pharmacy pharmacy = pharmacyDao.findByCode(code);
		return pharmacy;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipClaimService#update(com.pfchoice.core
	 * .entity.MembershipClaim)
	 */
	@Override
	public Integer loadDataCSV2Table(final String fileName, final String insuranceCode, final String tableNames) {
		return pharmacyDao.loadDataCSV2Table(fileName, insuranceCode, tableNames);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimService#isDataExists()
	 */
	@Override
	@Transactional(readOnly = true)
	public Boolean isDataExists(final String tableName) {
		return pharmacyDao.isDataExists(tableName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimService#loadData(java.lang.
	 * Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId,final Integer insId, final String insuranceCode) {
		return pharmacyDao.loadData(fileId, insId, insuranceCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimService#loadData(java.lang.
	 * Integer)
	 */
	@Override
	public Integer unloadCSV2Table(String tableName) {
		return pharmacyDao.unloadCSV2Table(tableName);
	}
}
