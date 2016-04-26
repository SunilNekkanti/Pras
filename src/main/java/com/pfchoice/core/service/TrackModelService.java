package com.pfchoice.core.service;

import com.pfchoice.core.entity.TrackModel;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface TrackModelService {

	/**
	 * @param id
	 * @return
	 */
	TrackModel deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	TrackModel findById(Integer id);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * @param bean
	 * @return
	 */
	TrackModel save(TrackModel bean);

	/**
	 * @param bean
	 * @return
	 */
	TrackModel update(TrackModel bean);

}
