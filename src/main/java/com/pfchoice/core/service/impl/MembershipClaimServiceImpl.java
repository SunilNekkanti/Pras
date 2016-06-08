package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipClaimDao;
import com.pfchoice.core.entity.MembershipClaim;
import com.pfchoice.core.service.MembershipClaimService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class MembershipClaimServiceImpl implements MembershipClaimService {

	@Autowired
	private MembershipClaimDao membershipClaimDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipClaimService#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public MembershipClaim deleteById(final Integer id) {
		return membershipClaimDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimService#findById(java.lang.
	 * Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public MembershipClaim findById(final Integer id) {
		return membershipClaimDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return membershipClaimDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipClaimService#save(com.pfchoice.core.
	 * entity.MembershipClaim)
	 */
	@Override
	public MembershipClaim save(final MembershipClaim bean) {
		return membershipClaimDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipClaimService#update(com.pfchoice.core
	 * .entity.MembershipClaim)
	 */
	@Override
	public MembershipClaim update(final MembershipClaim bean) {
		Updater<MembershipClaim> updater = new Updater<>(bean);
		return membershipClaimDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipClaimService#update(com.pfchoice.core
	 * .entity.MembershipClaim)
	 */
	@Override
	public Integer loadDataCSV2Table(final String fileName) {
		return membershipClaimDao.loadDataCSV2Table(fileName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimService#isDataExists()
	 */
	@Override
	public Boolean isDataExists() {
		return membershipClaimDao.isDataExists();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimService#loadData(java.lang.
	 * Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId, final String tableName) {
		return membershipClaimDao.loadData(fileId, tableName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimService#loadData(java.lang.
	 * Integer)
	 */
	@Override
	public Integer unloadCSV2Table(String tableName) {
		return membershipClaimDao.unloadCSV2Table(tableName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipClaimService#updateData(java.lang.
	 * Integer)
	 */
	@Override
	public Integer updateData(final Integer fileId, final String tableName) {
		return membershipClaimDao.updateData(fileId, tableName);
	}
}
