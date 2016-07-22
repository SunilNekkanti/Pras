package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.Date;

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
		Updater<MembershipClaim> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
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
	public Integer loadDataCSV2Table(final String fileName, final String tableName) {
		return membershipClaimDao.loadDataCSV2Table(fileName, tableName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimService#isDataExists()
	 */
	@Override
	@Transactional(readOnly = true)
	public Boolean isDataExists(final String tableName) {
		return membershipClaimDao.isDataExists(tableName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimService#loadData(java.lang.
	 * Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId,final Integer insId, final String insuranceCode) {
		return membershipClaimDao.loadData(fileId, insId, insuranceCode);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimService#getPage(int, int,
	 * java.lang.String, int, int, java.lang.String, java.lang.String,
	 * java.util.Date, java.util.Date, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getClaimPage(final int pageNo, final int pageSize, final String sSearch, final int sSearchIns,
			final int sSearchPrvdr, final String sort, final String sortdir, final Date processingFrom,
			final Date processingTo, final int processClaim) {
		return membershipClaimDao.getClaimPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr, sort, sortdir,
				processingFrom, processingTo, processClaim);
	}
}
