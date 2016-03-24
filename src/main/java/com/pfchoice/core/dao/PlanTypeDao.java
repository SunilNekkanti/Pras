package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.PlanType;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface PlanTypeDao
{

	PlanType deleteById(Integer id);

	PlanType findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    PlanType save(PlanType bean);

    PlanType updateByUpdater(Updater<PlanType> updater);
    
    List<PlanType> findAll();

}
