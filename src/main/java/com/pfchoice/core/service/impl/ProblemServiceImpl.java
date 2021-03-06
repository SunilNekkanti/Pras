package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.ProblemDao;
import com.pfchoice.core.entity.Problem;
import com.pfchoice.core.service.ProblemService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	private ProblemDao problemDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.ProblemService#deleteById(java.lang.Integer)
	 */
	@Override
	public Problem deleteById(final Integer id) {
		return problemDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.ProblemService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public Problem findById(final Integer id) {
		return problemDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.ProblemService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return problemDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.ProblemService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir, final int insId, final int effYear) {
		return problemDao.getPage(pageNo, pageSize, sSearch, sort, sortdir, insId, effYear);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.ProblemService#save(com.pfchoice.core.entity.
	 * Problems)
	 */
	@Override
	public Problem save(final Problem bean) {
		return problemDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.ProblemService#update(com.pfchoice.core.entity.
	 * Problems)
	 */
	@Override
	public Problem update(final Problem bean) {
		Updater<Problem> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return problemDao.updateByUpdater(updater);
	}
}
