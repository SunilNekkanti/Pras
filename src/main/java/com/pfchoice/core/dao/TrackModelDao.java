package com.pfchoice.core.dao;

import com.pfchoice.core.entity.TrackModel;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface TrackModelDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	TrackModel deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	TrackModel findById(Integer id);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * 
	 * @param bean
	 * @return
	 */
	TrackModel save(TrackModel bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	TrackModel updateByUpdater(Updater<TrackModel> updater);

}
