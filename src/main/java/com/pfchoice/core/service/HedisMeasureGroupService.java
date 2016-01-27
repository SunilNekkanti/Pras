package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.HedisMeasureGroup;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface HedisMeasureGroupService
{

	HedisMeasureGroup deleteById(Integer id);

	HedisMeasureGroup findById(Integer id);

    Pagination getPage(int pageNo, int pageSize);

    HedisMeasureGroup save(HedisMeasureGroup bean);

    HedisMeasureGroup update(HedisMeasureGroup bean);
    
    List<HedisMeasureGroup> findAll();

}
