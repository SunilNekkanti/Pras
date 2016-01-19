package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.County;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface CountyService
{

	County deleteById(Integer id);

	County findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    County save(County bean);

    County update(County bean);
    
    List<County> findAll();

}
