package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.CPTMeasure;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface CPTMeasureDao
{

	CPTMeasure deleteById(Integer id);

	CPTMeasure findById(Integer id);

	 Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

    CPTMeasure save(CPTMeasure bean);

    CPTMeasure updateByUpdater(Updater<CPTMeasure> updater);
    
    List<CPTMeasure> findAll();

}
