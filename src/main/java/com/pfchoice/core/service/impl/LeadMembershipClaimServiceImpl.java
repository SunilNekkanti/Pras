package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.LeadMembershipClaimDao;
import com.pfchoice.core.entity.LeadMembershipClaim;
import com.pfchoice.core.service.LeadMembershipClaimService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class LeadMembershipClaimServiceImpl implements LeadMembershipClaimService {

	@Autowired
	private LeadMembershipClaimDao leadMembershipClaimDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipClaimService#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public LeadMembershipClaim deleteById(final Integer id) {
		return leadMembershipClaimDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimService#findById(java.lang.
	 * Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public LeadMembershipClaim findById(final Integer id) {
		return leadMembershipClaimDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return leadMembershipClaimDao.getPage(pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.LeadMembershipClaimService#getPage(int, int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, int leadMbrId) {
		return leadMembershipClaimDao.getPage(pageNo, pageSize,leadMbrId);
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipClaimService#save(com.pfchoice.core.
	 * entity.MembershipClaim)
	 */
	@Override
	public LeadMembershipClaim save(final LeadMembershipClaim bean) {
		return leadMembershipClaimDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipClaimService#update(com.pfchoice.core
	 * .entity.MembershipClaim)
	 */
	@Override
	public LeadMembershipClaim update(final LeadMembershipClaim bean) {
		Updater<LeadMembershipClaim> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return leadMembershipClaimDao.updateByUpdater(updater);
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
		return leadMembershipClaimDao.loadDataCSV2Table(fileName, insuranceCode, tableNames);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimService#isDataExists()
	 */
	@Override
	@Transactional(readOnly = true)
	public Boolean isDataExists(final String tableName) {
		return leadMembershipClaimDao.isDataExists(tableName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimService#loadData(java.lang.
	 * Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId,final Integer insId, final String insuranceCode,final Integer reportMonth) {
		return leadMembershipClaimDao.loadData(fileId, insId, insuranceCode, reportMonth);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimService#loadData(java.lang.
	 * Integer)
	 */
	@Override
	public Integer unloadCSV2Table(String tableName) {
		return leadMembershipClaimDao.unloadCSV2Table(tableName);
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
		return leadMembershipClaimDao.updateData(fileId, tableName);
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
		return leadMembershipClaimDao.getClaimPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr, sort, sortdir,
				processingFrom, processingTo, processClaim);
	}
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipClaimService#unloadTable(java.lang.String)
	 */
	@Override
	public Integer unloadTable(){
		return leadMembershipClaimDao.unloadTable();
	}
	
	public List<LeadMembershipClaim> getUnwantedClaims(Integer insId, Integer prvdrId, Integer reportMonth, Integer activityMonth, Boolean isUnwanted){
		
		return leadMembershipClaimDao.getUnwantedClaims(insId, prvdrId, reportMonth, activityMonth,  isUnwanted);
	}
}
