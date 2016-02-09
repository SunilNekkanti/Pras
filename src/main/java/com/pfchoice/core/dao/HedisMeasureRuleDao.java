package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.HedisMeasureRule;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface HedisMeasureRuleDao
{

	HedisMeasureRule deleteById(Integer id);

	HedisMeasureRule findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    HedisMeasureRule save(HedisMeasureRule bean);

    HedisMeasureRule updateByUpdater(Updater<HedisMeasureRule> updater);
    
    List<HedisMeasureRule> findAll();

}