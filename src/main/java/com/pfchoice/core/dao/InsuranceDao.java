package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.Insurance;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface InsuranceDao
{

	Insurance deleteById(Integer id);

	Insurance findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    Insurance save(Insurance bean);

    Insurance updateByUpdater(Updater<Insurance> updater);
    
    List<Insurance> findAll();

}