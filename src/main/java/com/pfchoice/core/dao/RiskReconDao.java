package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.RiskRecon;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
public interface RiskReconDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	RiskRecon deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	RiskRecon findById(Integer id);

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
	RiskRecon save(RiskRecon bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	RiskRecon updateByUpdater(Updater<RiskRecon> updater);
	
	/**
	 * @param tableName
	 * @param insId
	 * @param prvdrId
	 * @param repGenDate
	 * @param category
	 * @param adminRole
	 * @return
	 */
	List<Object[]> claimReportQuery(String tableName, Integer insId, Integer prvdrId, String repGenDate, String category, String adminRole, String rosterCap);


}
