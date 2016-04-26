package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.ZipCode;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface ZipCodeService {

	/**
	 * @param id
	 * @return
	 */
	ZipCode deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	ZipCode findById(Integer id);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * @param bean
	 * @return
	 */
	ZipCode save(ZipCode bean);

	/**
	 * @param bean
	 * @return
	 */
	ZipCode update(ZipCode bean);

	/**
	 * @param stateCode
	 * @return
	 */
	List<ZipCode> findByStateCode(Integer stateCode);
}
