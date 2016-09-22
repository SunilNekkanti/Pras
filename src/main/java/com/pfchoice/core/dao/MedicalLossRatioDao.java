package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.MedicalLossRatio;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Sarath
 */
/**
 * @author Ecomed PHP
 *
 */
public interface MedicalLossRatioDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	MedicalLossRatio deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	MedicalLossRatio findById(Integer id);

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
	 * @param insId
	 * @param prvdrId
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getMlrReportDate(Integer pageNo, Integer pageSize, Integer insId, Integer prvdrId, String sort, String sortdir);

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param insId
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	Pagination getPage(Integer pageNo, Integer pageSize, Integer insId, Integer prvdrId, String sSearch, String sort, String sortdir);

	/**
	 * 
	 * @param bean
	 * @return
	 */
	MedicalLossRatio save(MedicalLossRatio bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	MedicalLossRatio updateByUpdater(Updater<MedicalLossRatio> updater);

	/**
	 * @param fileId
	 * @return
	 */
	Integer loadData(Integer fileId);

	/**
	 * @param code
	 * @return
	 */
	MedicalLossRatio findByCode(String code);
	
	 /**
	 * @param tableName
	 * @return
	 */
	List<Object[]> reportQuery(String tableName, Integer insId, Integer prvdrId, String repGenDate);
}
