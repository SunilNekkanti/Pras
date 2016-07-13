package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.ICDMeasure;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface ICDMeasureDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	ICDMeasure deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	ICDMeasure findById(Integer id);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

	/**
	 * 
	 * @param bean
	 * @return
	 */
	ICDMeasure save(ICDMeasure bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	ICDMeasure updateByUpdater(Updater<ICDMeasure> updater);

	/**
	 * 
	 * @param code
	 * @return
	 */
	ICDMeasure findByCode(String code);

	/**
	 * 
	 * @param code
	 * @return
	 */
	List<ICDMeasure> findByCodes(String[] icdCodes);

}
