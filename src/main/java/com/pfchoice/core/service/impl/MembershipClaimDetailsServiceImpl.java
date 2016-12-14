package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipClaimDetailsDao;
import com.pfchoice.core.entity.MembershipClaimDetails;
import com.pfchoice.core.service.MembershipClaimDetailsService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class MembershipClaimDetailsServiceImpl implements MembershipClaimDetailsService {

	@Autowired
	private MembershipClaimDetailsDao membershipClaimDetailsDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipClaimDetailsService#deleteById(java.
	 * lang.Integer)
	 */
	@Override
	public MembershipClaimDetails deleteById(final Integer id) {
		return membershipClaimDetailsDao.deleteById(id);
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
	public MembershipClaimDetails findById(final Integer id) {
		return membershipClaimDetailsDao.findById(id);
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
		return membershipClaimDetailsDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipClaimDetailsService#save(com.pfchoice
	 * .core.entity.MembershipClaimDetails)
	 */
	@Override
	public MembershipClaimDetails save(final MembershipClaimDetails bean) {
		return membershipClaimDetailsDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimDetailsService#update(com.
	 * pfchoice.core.entity.MembershipClaimDetails)
	 */
	@Override
	public MembershipClaimDetails update(final MembershipClaimDetails bean) {
		Updater<MembershipClaimDetails> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return membershipClaimDetailsDao.updateByUpdater(updater);
	}

	@Override
	public Integer loadData(final Integer fileId, final Integer insId, final Integer reportMonth) {
		return membershipClaimDetailsDao.loadData(fileId, insId, reportMonth);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimDetailsService#
	 * getMbrClaimDetailsPage(int)
	 */
	@Override
	public Pagination getMbrClaimDetailsPage(final int mbrHosId) {
		return membershipClaimDetailsDao.getMbrClaimDetailsPage(mbrHosId);
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
		return membershipClaimDetailsDao.getMbrClaimDetailsByActivityMonth(pageNo, pageSize, sSearch, sSearchIns,
				sSearchPrvdr, sort, sortdir, monthPicker, processClaim);
	}
}
