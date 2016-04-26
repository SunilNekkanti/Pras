package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.EthinicityDao;
import com.pfchoice.core.entity.Ethinicity;
import com.pfchoice.core.service.EthinicityService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class EthinicityServiceImpl implements EthinicityService {

	@Autowired
	private EthinicityDao ethinicityDao;

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.EthinicityService#deleteById(java.lang.Byte)
	 */
	@Override
	public Ethinicity deleteById(Byte id) {
		return ethinicityDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.EthinicityService#findById(java.lang.Byte)
	 */
	@Override
	@Transactional(readOnly = true)
	public Ethinicity findById(final Byte id) {
		return ethinicityDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.EthinicityService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return ethinicityDao.getPage(pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.EthinicityService#save(com.pfchoice.core.entity.Ethinicity)
	 */
	@Override
	public Ethinicity save(final Ethinicity bean) {
		return ethinicityDao.save(bean);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.EthinicityService#update(com.pfchoice.core.entity.Ethinicity)
	 */
	@Override
	public Ethinicity update(final Ethinicity bean) {
		Updater<Ethinicity> updater = new Updater<>(bean);
		return ethinicityDao.updateByUpdater(updater);
	}

}
