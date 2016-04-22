package com.pfchoice.core.service;

import com.pfchoice.core.entity.TrackModel;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface TrackModelService {

	TrackModel deleteById(Integer id);

	TrackModel findById(Integer id);

	Pagination getPage(int pageNo, int pageSize);

	TrackModel save(TrackModel bean);

	TrackModel update(TrackModel bean);

}
