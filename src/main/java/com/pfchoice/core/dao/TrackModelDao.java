package com.pfchoice.core.dao;

import com.pfchoice.core.entity.TrackModel;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface TrackModelDao
{

	TrackModel deleteById(Integer id);

	TrackModel findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    TrackModel save(TrackModel bean);

    TrackModel updateByUpdater(Updater<TrackModel> updater);

}
