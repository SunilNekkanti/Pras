package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.LeadMembershipProviderDao;
import com.pfchoice.core.entity.LeadMembershipProvider;
import com.pfchoice.core.service.LeadMembershipProviderService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class LeadMembershipProviderServiceImpl implements LeadMembershipProviderService {

	@Autowired
	private LeadMembershipProviderDao leadMembershipProviderService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipProviderService#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public LeadMembershipProvider deleteById(final Integer id) {
		return leadMembershipProviderService.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipProviderService#findById(java.lang.
	 * Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public LeadMembershipProvider findById(final Integer id) {
		return leadMembershipProviderService.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.LeadMembershipProviderService#getPage(int,
	 * int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return leadMembershipProviderService.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipProviderService#save(com.pfchoice.
	 * core.entity.LeadMembershipProvider)
	 */
	@Override
	public LeadMembershipProvider save(final LeadMembershipProvider bean) {
		return leadMembershipProviderService.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipProviderService#update(com.pfchoice.
	 * core.entity.LeadMembershipProvider)
	 */
	@Override
	public LeadMembershipProvider update(final LeadMembershipProvider bean) {
		Updater<LeadMembershipProvider> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return leadMembershipProviderService.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipProviderService#findAllByMbrId(java.
	 * lang.Integer)
	 */
	@Override
	public List<LeadMembershipProvider> findAllByMbrId(final Integer id) {
		return leadMembershipProviderService.findAllByMbrId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.LeadMembershipProviderService#findByMbrId(java.lang
	 * .Integer)
	 */
	@Override
	public LeadMembershipProvider findByMbrId(final Integer id) {
		return leadMembershipProviderService.findByMbrId(id);
	}
}
