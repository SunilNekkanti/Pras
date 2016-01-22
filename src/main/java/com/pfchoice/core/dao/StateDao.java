package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.State;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface StateDao
{

	State deleteById(Integer id);

	State findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    State save(State bean);

    State updateByUpdater(Updater<State> updater);
    
    List<State> findAll();

}
