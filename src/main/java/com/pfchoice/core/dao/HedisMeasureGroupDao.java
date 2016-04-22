package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.HedisMeasureGroup;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface HedisMeasureGroupDao {

	HedisMeasureGroup deleteById(Integer id);

	HedisMeasureGroup findById(Integer id);

	Pagination getPage(int pageNo, int pageSize);

	HedisMeasureGroup save(HedisMeasureGroup bean);

	HedisMeasureGroup updateByUpdater(Updater<HedisMeasureGroup> updater);

	List<HedisMeasureGroup> findAll();

}
