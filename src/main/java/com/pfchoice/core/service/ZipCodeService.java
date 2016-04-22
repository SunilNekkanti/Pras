package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.ZipCode;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface ZipCodeService {

	ZipCode deleteById(Integer id);

	ZipCode findById(Integer id);

	Pagination getPage(int pageNo, int pageSize);

	ZipCode save(ZipCode bean);

	ZipCode update(ZipCode bean);

	List<ZipCode> findAll();

	List<ZipCode> findByStateCode(Integer stateCode);
}
