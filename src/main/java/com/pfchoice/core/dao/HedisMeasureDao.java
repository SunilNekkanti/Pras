package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.HedisMeasure;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface HedisMeasureDao
{

	HedisMeasure deleteById(Integer id);

	HedisMeasure findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    HedisMeasure save(HedisMeasure bean);

    HedisMeasure updateByUpdater(Updater<HedisMeasure> updater);
    
    List<HedisMeasure> findAll();

}
