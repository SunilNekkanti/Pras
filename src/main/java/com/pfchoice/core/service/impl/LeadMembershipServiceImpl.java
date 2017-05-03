package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.LeadMembershipDao;
import com.pfchoice.core.entity.LeadMembership;
import com.pfchoice.core.service.LeadMembershipService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class LeadMembershipServiceImpl implements LeadMembershipService {

	@Autowired
	private LeadMembershipDao leadMembershipDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipService#deleteById(java.lang.Integer)
	 */
	@Override
	public LeadMembership deleteById(final Integer id) {
		return leadMembershipDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public LeadMembership findById(final Integer id) {
		return leadMembershipDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipService#getPage(Integer, Integer,
	 * java.lang.String, Integer, Integer, Integer, java.util.List, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final Integer pageNo, final Integer pageSize, final String sSearch, final Integer sSearchIns,
			final Integer sSearchPrvdr, final Integer sSearchHedisCode, final List<Integer> ruleIds, final String sort,
			final String sortdir) {
		return leadMembershipDao.getPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr, sSearchHedisCode, ruleIds,
				sort, sortdir);
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final Integer pageNo, final Integer pageSize ) {
		return leadMembershipDao.getPage(pageNo, pageSize );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipService#save(com.pfchoice.core.entity
	 * .Membership)
	 */
	@Override
	public LeadMembership save(final LeadMembership bean) {
		return leadMembershipDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipService#update(com.pfchoice.core.
	 * entity.Membership)
	 */
	@Override
	public LeadMembership update(final LeadMembership bean) {
		Updater<LeadMembership> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return leadMembershipDao.updateByUpdater(updater);
	}
	
	
	/**
	 * @param lead
	 * @return
	 */
	@Override
	public boolean isLeadExist(LeadMembership lead){
		return leadMembershipDao.isLeadExist(lead);
		
	}
	
}
