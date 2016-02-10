package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.ICDMeasure;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface ICDMeasureDao
{

	ICDMeasure deleteById(Integer id);

	ICDMeasure findById(Integer id);

    Pagination getPage(int pageNo, int pageSize, String sSearch);
    
    ICDMeasure save(ICDMeasure bean);

    ICDMeasure updateByUpdater(Updater<ICDMeasure> updater);
    
    List<ICDMeasure> findAll();

}
