package com.pfchoice.core.service;

import com.pfchoice.core.entity.Provider;

import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface ProviderService {

	/**
	 * @param id
	 * @return
	 */
	Provider deleteById(Integer id);

	/**
	 * @param id
	 * @return
	 */
	Provider findById(Integer id);

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
	Provider save(Provider bean);

	/**
	 * @param bean
	 * @return
	 */
	Provider update(Provider bean);

	/**
	 * @param id
	 * @return
	 */
	Pagination findByInsId(Integer id);
	
	/**
	 * @param id
	 * @param npi
	 * @return
	 */
	boolean isUniquePrvdrNPI(Integer id, String npi);
	
	/**
	 * @param npi
	 * @return
	 */
	Provider findByPrvdrNPI(String npi);

}
