package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.LeadMembershipHedisMeasureDao;
import com.pfchoice.core.entity.LeadMembershipHedisMeasure;
import com.pfchoice.core.service.LeadMembershipHedisMeasureService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class LeadMembershipHedisMeasureServiceImpl implements LeadMembershipHedisMeasureService {

	@Autowired
	private LeadMembershipHedisMeasureDao leadMembershipHedisMeasureDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipHedisMeasureService#deleteById(java.
	 * lang.Integer)
	 */
	@Override
	public LeadMembershipHedisMeasure deleteById(final Integer id) {
		return leadMembershipHedisMeasureDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipHedisMeasureService#findById(java.
	 * lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public LeadMembershipHedisMeasure findById(final Integer id) {
		return leadMembershipHedisMeasureDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.LeadMembershipHedisMeasureService#getPage(int,
	 * int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return leadMembershipHedisMeasureDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.LeadMembershipHedisMeasureService#getPage(int,
	 * int, java.lang.String, int, int, int, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final int sSearchIns,
			final int sSearchPrvdr, final int sSearchHedisCode, final String sort, final String sortdir) {
		return leadMembershipHedisMeasureDao.getPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr, sSearchHedisCode,
				sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipHedisMeasureService#save(com.pfchoice
	 * .core.entity.LeadMembershipHedisMeasure)
	 */
	@Override
	public LeadMembershipHedisMeasure save(final LeadMembershipHedisMeasure bean) {
		return leadMembershipHedisMeasureDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.LeadMembershipHedisMeasureService#update(com.
	 * pfchoice.core.entity.LeadMembershipHedisMeasure)
	 */
	@Override
	public LeadMembershipHedisMeasure update(final LeadMembershipHedisMeasure bean) {
		Updater<LeadMembershipHedisMeasure> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return leadMembershipHedisMeasureDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.LeadMembershipHedisMeasureService#
	 * findByMbrIdAndRuleId(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Pagination findByMbrIdAndRuleId(final Integer mbrId, final Integer ruleId) {
		return leadMembershipHedisMeasureDao.findByMbrIdAndRuleId(mbrId, ruleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipHedisMeasureService#loadData(java.
	 * lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId, final Integer insId, final Integer leadMbrId, final String insuranceCode, Integer reportMonth) {
		return leadMembershipHedisMeasureDao.loadData(fileId, insId, leadMbrId, insuranceCode, reportMonth);
	}
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.LeadMembershipHedisMeasureService#unloadTable()
	 */
	@Override
	public Integer unloadTable(final  Integer insId, final String insuranceCode) {
		return leadMembershipHedisMeasureDao.unloadTable(insId, insuranceCode);
	}
}
