package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.County;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface CountyDao
{

	County deleteById(Integer id);

	County findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    County save(County bean);

    County updateByUpdater(Updater<County> updater);
    
    List<County> findAll();

}
