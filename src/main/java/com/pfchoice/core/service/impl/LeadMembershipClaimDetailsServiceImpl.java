package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.LeadMembershipClaimDetailsDao;
import com.pfchoice.core.entity.LeadMembershipClaimDetails;
import com.pfchoice.core.service.LeadMembershipClaimDetailsService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class LeadMembershipClaimDetailsServiceImpl implements LeadMembershipClaimDetailsService {

	@Autowired
	private LeadMembershipClaimDetailsDao leadMembershipClaimDetailsDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipClaimDetailsService#deleteById(java.
	 * lang.Integer)
	 */
	@Override
	public LeadMembershipClaimDetails deleteById(final Integer id) {
		return leadMembershipClaimDetailsDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipClaimDetailsService#findById(java.
	 * lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public LeadMembershipClaimDetails findById(final Integer id) {
		return leadMembershipClaimDetailsDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimDetailsService#getPage(int,
	 * int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return leadMembershipClaimDetailsDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipClaimDetailsService#save(com.pfchoice
	 * .core.entity.MembershipClaimDetails)
	 */
	@Override
	public LeadMembershipClaimDetails save(final LeadMembershipClaimDetails bean) {
		return leadMembershipClaimDetailsDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimDetailsService#update(com.
	 * pfchoice.core.entity.MembershipClaimDetails)
	 */
	@Override
	public LeadMembershipClaimDetails update(final LeadMembershipClaimDetails bean) {
		Updater<LeadMembershipClaimDetails> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return leadMembershipClaimDetailsDao.updateByUpdater(updater);
	}

	@Override
	public Integer loadData(final Integer fileId, final Integer insId, final Integer reportMonth) {
		return leadMembershipClaimDetailsDao.loadData(fileId, insId, reportMonth);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimDetailsService#
	 * getMbrClaimDetailsPage(int)
	 */
	@Override
	public Pagination getMbrClaimDetailsPage(final int mbrHosId) {
		return leadMembershipClaimDetailsDao.getMbrClaimDetailsPage(mbrHosId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimDetailsService#getPage(int,
	 * int, java.lang.String, int, int, java.lang.String, java.lang.String,
	 * java.util.Date, java.util.Date, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getMbrClaimDetailsByActivityMonth(final int pageNo, final int pageSize, final String sSearch,
			final int sSearchIns, final int sSearchPrvdr, final String sort, final String sortdir,
			final List<Integer> monthPicker, final int processClaim) {
		return leadMembershipClaimDetailsDao.getMbrClaimDetailsByActivityMonth(pageNo, pageSize, sSearch, sSearchIns,
				sSearchPrvdr, sort, sortdir, monthPicker, processClaim);
	}
}
