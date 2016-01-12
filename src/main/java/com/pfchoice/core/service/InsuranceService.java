package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.Insurance;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface InsuranceService
{

	Insurance deleteById(Integer id);

	Insurance findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    Insurance save(Insurance bean);

    Insurance update(Insurance bean);
    
    List<Insurance> findAll();

}
