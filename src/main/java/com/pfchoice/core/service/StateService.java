package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.State;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface StateService
{

	State deleteById(Integer id);

	State findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    State save(State bean);

    State update(State bean);
    
    List<State> findAll();

}
