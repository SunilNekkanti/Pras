package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipDao;
import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.service.MembershipService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class MembershipServiceImpl implements MembershipService {

	@Autowired
	private MembershipDao membershipDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipService#deleteById(java.lang.Integer)
	 */
	@Override
	public Membership deleteById(final Integer id) {
		return membershipDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public Membership findById(final Integer id) {
		return membershipDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return membershipDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipService#getPage(int, int,
	 * java.lang.String, int, int, int, java.util.List, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final int sSearchIns,
			final int sSearchPrvdr, final int sSearchHedisCode, final List<Integer> ruleIds, final String sort,
			final String sortdir) {
		return membershipDao.getPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr, sSearchHedisCode, ruleIds,
				sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipService#getPage(int, int,
	 * java.lang.String, int, int, int, java.util.List, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getMembershipActivityMonthPage(final int pageNo, final int pageSize, final String sSearch,
			final int sSearchIns, final int sSearchPrvdr, final int sSearchHedisCode, final List<Integer> ruleIds,
			final String sort, final String sortdir) {
		return membershipDao.getMembershipActivityMonthPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr,
				sSearchHedisCode, ruleIds, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipService#getPage(int, int,
	 * java.lang.String, int, int, int, java.util.List, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getMembershipProblemPage(final int pageNo, final int pageSize, final String sSearch,
			final int sSearchIns, final int sSearchPrvdr, final int sSearchPbmCode, final List<Integer> ruleIds,
			final String sort, final String sortdir) {
		return membershipDao.getMembershipProblemPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr,
				sSearchPbmCode, ruleIds, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipService#getPage(int, int,
	 * java.lang.String, int, int, java.lang.String, java.lang.String,
	 * java.util.Date, java.util.Date, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final int sSearchIns,
			final int sSearchPrvdr, final String sort, final String sortdir, final Date processingFrom,
			final Date processingTo, final int processHospitalization) {
		return membershipDao.getPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr, sort, sortdir, processingFrom,
				processingTo, processHospitalization);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipService#getPage(int, int,
	 * java.lang.String, int, int, java.lang.String, java.lang.String,
	 * java.util.Date, java.util.Date, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getClaimPage(final int pageNo, final int pageSize, final String sSearch, final int sSearchIns,
			final int sSearchPrvdr, final String sort, final String sortdir, final Date processingFrom,
			final Date processingTo, final int processClaim) {
		return membershipDao.getClaimPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr, sort, sortdir,
				processingFrom, processingTo, processClaim);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipService#save(com.pfchoice.core.entity
	 * .Membership)
	 */
	@Override
	public Membership save(final Membership bean) {
		return membershipDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipService#update(com.pfchoice.core.
	 * entity.Membership)
	 */
	@Override
	public Membership update(final Membership bean) {
		Updater<Membership> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return membershipDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipService#update(com.pfchoice.core
	 * .entity.MembershipClaim)
	 */
	@Override
	public Integer loadDataCSV2Table(final String fileName, final String tableName) {
		return membershipDao.loadDataCSV2Table(fileName, tableName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipService#isDataExists()
	 */
	@Override
	@Transactional(readOnly = true)
	public Boolean isDataExists(final String tableName) {
		return membershipDao.isDataExists(tableName);
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
		return membershipDao.loadData(insId, fileId, activityMonth, tableName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipService#loadData(java.lang.
	 * Integer)
	 */
	@Override
	public Integer unloadCSV2Table(String tableName) {
		return membershipDao.unloadCSV2Table(tableName);
	}
	
	public Pagination getNewHedisMeasure(final int pageNo, final int pageSize, final String sSearch,
			final int sSearchIns, final int sSearchPrvdr, final String sSearchHedisRule, final String sSearchReportMonth,
			final Date sSearchStartDate, final Date sSearchEndDate, final String sSearchRoster, final String sSearchCap,
			final String userName, final String sort, final String sortdir
			){
		
		return membershipDao.getNewHedisMeasure(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr,
				sSearchHedisRule, sSearchReportMonth, sSearchStartDate, sSearchEndDate, sSearchRoster, sSearchCap, 
				userName, sort, sortdir);
	}
	
	public Pagination getMbrNewHedisMeasure(final int pageNo, final int pageSize, int sSearchMbrId,
			final int sSearchRuleId, final String userName, final String sort, final String sortdir
			){
		
		return membershipDao.getMbrNewHedisMeasure(pageNo, pageSize, sSearchMbrId, sSearchRuleId,
				userName, sort, sortdir);
	}
}
