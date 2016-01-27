package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.HedisMeasure;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface HedisMeasureService
{

	HedisMeasure deleteById(Integer id);

	HedisMeasure findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    HedisMeasure save(HedisMeasure bean);

    HedisMeasure update(HedisMeasure bean);
    
    List<HedisMeasure> findAll();

}
