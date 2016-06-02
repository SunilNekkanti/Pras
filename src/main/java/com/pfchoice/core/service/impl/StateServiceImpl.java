package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.StateDao;
import com.pfchoice.core.entity.State;
import com.pfchoice.core.service.StateService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class StateServiceImpl implements StateService {

	@Autowired
	private StateDao stateDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.StateService#deleteById(java.lang.Integer)
	 */
	@Override
	public State deleteById(final Integer id) {
		return stateDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.StateService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public State findById(final Integer id) {
		return stateDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.StateService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return stateDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.StateService#save(com.pfchoice.core.entity.
	 * State)
	 */
	@Override
	public State save(final State bean) {
		return stateDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.StateService#update(com.pfchoice.core.entity.
	 * State)
	 */
	@Override
	public State update(final State bean) {
		Updater<State> updater = new Updater<>(bean);
		return stateDao.updateByUpdater(updater);
	}

}
