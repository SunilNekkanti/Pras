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

	@Override
	public TrackModel deleteById(final Integer id) {
		// Used for transaction test
		return trackModelDao.deleteById(id);
		// throw new UnsupportedOperationException();
	}

	@Override
	@Transactional(readOnly = true)
	public TrackModel findById(final Integer id) {
		return trackModelDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return trackModelDao.getPage(pageNo, pageSize);
	}

	@Override
	public TrackModel save(final TrackModel bean) {
		// Used for transaction test
		return trackModelDao.save(bean);
		// this.deleteById(1);
		// return null;
	}

	@Override
	public TrackModel update(final TrackModel bean) {
		Updater<TrackModel> updater = new Updater<>(bean);
		return trackModelDao.updateByUpdater(updater);
	}

}
