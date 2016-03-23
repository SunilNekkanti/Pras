package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.HedisMeasureRule;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface HedisMeasureRuleService
{

	HedisMeasureRule deleteById(Integer id);

	HedisMeasureRule findById(Integer id);

	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);
	
	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir, int insId, int effYear);

    HedisMeasureRule save(HedisMeasureRule bean);

    HedisMeasureRule update(HedisMeasureRule bean);
    
    List<HedisMeasureRule> findAll();
    
    List<HedisMeasureRule> findAllByInsId(Integer insId);
}
