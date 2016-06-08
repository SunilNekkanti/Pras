package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipProblemDao;
import com.pfchoice.core.entity.MembershipProblem;
import com.pfchoice.core.service.MembershipProblemService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class MembershipProblemServiceImpl implements MembershipProblemService {

	@Autowired
	private MembershipProblemDao mbrProblemDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipProblemService#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public MembershipProblem deleteById(final Integer id) {
		return mbrProblemDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipProblemService#findById(java.lang.
	 * Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public MembershipProblem findById(final Integer id) {
		return mbrProblemDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipProblemService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return mbrProblemDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipProblemService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return mbrProblemDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipProblemService#save(com.pfchoice.core
	 * .entity.Problems)
	 */
	@Override
	public MembershipProblem save(final MembershipProblem bean) {
		return mbrProblemDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipProblemService#update(com.pfchoice.
	 * core.entity.Problems)
	 */
	@Override
	public MembershipProblem update(final MembershipProblem bean) {
		Updater<MembershipProblem> updater = new Updater<>(bean);
		return mbrProblemDao.updateByUpdater(updater);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipClaimService#loadData(java.lang.
	 * Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId, final String tableName) {
		return mbrProblemDao.loadData(fileId, tableName);
	}
}
