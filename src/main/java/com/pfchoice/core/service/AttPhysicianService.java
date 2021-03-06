package com.pfchoice.core.service;

import com.pfchoice.core.entity.AttPhysician;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author sarath
 */
public interface AttPhysicianService {

	/**
	 * @param id
	 * @return
	 */
	AttPhysician deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	AttPhysician findById(Integer id);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

	/**
	 * @param bean
	 * @return
	 */
	AttPhysician save(AttPhysician bean);

	/**
	 * @param bean
	 * @return
	 */
	AttPhysician update(AttPhysician bean);

	/**
	 * @param fileId
	 * @return
	 */
	Integer loadData(Integer fileId);

	/**
	 * @param code
	 * @return
	 */
	AttPhysician findByCode(String code);

	/**
	 * @param id
	 * @param code
	 * @return
	 */
	boolean isCodeUnique(Integer id, String code);
}
