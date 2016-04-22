package com.pfchoice.core.dao;

import com.pfchoice.core.entity.Provider;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

/**
 *
 * @author Mohanasundharam
 */
public interface ProviderDao {

	Provider deleteById(Integer id);

	Provider findById(Integer id);

	Pagination getPage(int pageNo, int pageSize, String sSearch, String sort, String sortdir);

	Provider save(Provider bean);

	Provider updateByUpdater(Updater<Provider> updater);

	Pagination findByInsId(Integer id);
}
