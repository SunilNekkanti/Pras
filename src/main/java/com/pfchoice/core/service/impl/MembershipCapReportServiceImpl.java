package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipCapReportDao;
import com.pfchoice.core.entity.MembershipCapReport;
import com.pfchoice.core.service.MembershipCapReportService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class MembershipCapReportServiceImpl implements MembershipCapReportService {

	@Autowired
	private MembershipCapReportDao membershipCapReportDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipCapReportService#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public MembershipCapReport deleteById(final Integer id) {
		return membershipCapReportDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipCapReportService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public MembershipCapReport findById(final Integer id) {
		return membershipCapReportDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipCapReportService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return membershipCapReportDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipCapReportService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return membershipCapReportDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipCapReportService#save(com.pfchoice.core.
	 * entity.MembershipCapReport)
	 */
	@Override
	public MembershipCapReport save(final MembershipCapReport bean) {
		return membershipCapReportDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipCapReportService#update(com.pfchoice.core.
	 * entity.MembershipCapReport)
	 */
	@Override
	public MembershipCapReport update(final MembershipCapReport bean) {
		Updater<MembershipCapReport> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return membershipCapReportDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipService#loadData(java.lang.
	 * Integer)
	 */
	@Override
	public Integer loadData(final Integer insId, final Integer fileId, final Integer activityMonth,
			final String tableName) {
		return membershipCapReportDao.loadData(insId, fileId, activityMonth, tableName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipService#update(com.pfchoice.core
	 * .entity.MembershipClaim)
	 */
	@Override
	public Integer loadDataCSV2Table(final String fileName, final String tableName) {
		return membershipCapReportDao.loadDataCSV2Table(fileName, tableName);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipService#loadData(java.lang.
	 * Integer)
	 */
	@Override
	public Integer unloadCSV2Table(String tableName) {
		return membershipCapReportDao.unloadCSV2Table(tableName);
	}
}
