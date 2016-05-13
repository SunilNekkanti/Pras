package com.pfchoice.core.dao;

import com.pfchoice.core.entity.Provider;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface ProviderDao {

	/**
	 * 
	 * @param id
	 * @return
	 */
	Provider deleteById(Integer id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Provider findById(Integer id);

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
	Provider save(Provider bean);

	/**
	 * 
	 * @param updater
	 * @return
	 */
	Provider updateByUpdater(Updater<Provider> updater);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Pagination findByInsId(Integer id);
	
	/**
	 * @param npi
	 * @return
	 */
	Provider findByPrvdrNPI(String npi);
}
