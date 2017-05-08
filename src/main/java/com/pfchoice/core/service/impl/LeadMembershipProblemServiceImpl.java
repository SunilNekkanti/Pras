package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.LeadMembershipProblemDao;
import com.pfchoice.core.entity.LeadMembership;
import com.pfchoice.core.entity.LeadMembershipProblem;
import com.pfchoice.core.entity.Problem;
import com.pfchoice.core.service.LeadMembershipProblemService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class LeadMembershipProblemServiceImpl implements LeadMembershipProblemService {

	@Autowired
	private LeadMembershipProblemDao leadMbrProblemDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipProblemService#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public LeadMembershipProblem deleteById(final Integer id) {
		return leadMbrProblemDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipProblemService#findById(java.lang.
	 * Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public LeadMembershipProblem findById(final Integer id) {
		return leadMbrProblemDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.LeadMembershipProblemService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return leadMbrProblemDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.LeadMembershipProblemService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return leadMbrProblemDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipProblemService#save(com.pfchoice.core
	 * .entity.Problems)
	 */
	@Override
	public LeadMembershipProblem save(final LeadMembershipProblem bean) {
		return leadMbrProblemDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipProblemService#update(com.pfchoice.
	 * core.entity.Problems)
	 */
	@Override
	public LeadMembershipProblem update(final LeadMembershipProblem bean) {
		Updater<LeadMembershipProblem> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return leadMbrProblemDao.updateByUpdater(updater);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.LeadMembershipProblemService#loadData(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId, final Integer insId, final String insuranceCode, Integer reportMonth) {
		return leadMbrProblemDao.loadData(fileId, insId, insuranceCode, reportMonth);
	}
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.LeadMembershipProblemService#findByMbrIdAndPbmId(com.pfchoice.core.entity.LeadMembership, com.pfchoice.core.entity.Problem, java.lang.Integer)
	 */
	@Override
	public Integer findByMbrIdAndPbmId(final LeadMembership mbrId, final Problem pbmId, final Integer id) {
		return leadMbrProblemDao.findByMbrIdAndPbmId(mbrId, pbmId, id);
	}
}
