package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.TrackModelDao;
import com.pfchoice.core.entity.TrackModel;
import com.pfchoice.core.service.TrackModelService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class TrackModelServiceImpl implements TrackModelService {

	@Autowired
	private TrackModelDao trackModelDao;

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.TrackModelService#deleteById(java.lang.Integer)
	 */
	@Override
	public TrackModel deleteById(final Integer id) {
		return trackModelDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.TrackModelService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public TrackModel findById(final Integer id) {
		return trackModelDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.TrackModelService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return trackModelDao.getPage(pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.TrackModelService#save(com.pfchoice.core.entity.TrackModel)
	 */
	@Override
	public TrackModel save(final TrackModel bean) {
		return trackModelDao.save(bean);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.TrackModelService#update(com.pfchoice.core.entity.TrackModel)
	 */
	@Override
	public TrackModel update(final TrackModel bean) {
		Updater<TrackModel> updater = new Updater<>(bean);
		return trackModelDao.updateByUpdater(updater);
	}

}
