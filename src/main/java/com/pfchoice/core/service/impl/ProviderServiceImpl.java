package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.ProviderDao;
import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.service.ProviderService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	private ProviderDao providerDao;

	@Override
	public Provider deleteById(final Integer id) {
		// Used for transaction test
		return providerDao.deleteById(id);
		// throw new UnsupportedOperationException();
	}

	@Override
	@Transactional(readOnly = true)
	public Provider findById(final Integer id) {
		return providerDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return providerDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	@Override
	public Provider save(final Provider bean) {
		// Used for transaction test
		return providerDao.save(bean);
		// this.deleteById(1);
		// return null;
	}

	@Override
	public Provider update(final Provider bean) {
		Updater<Provider> updater = new Updater<>(bean);
		return providerDao.updateByUpdater(updater);
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination findByInsId(final Integer id) {
		return providerDao.findByInsId(id);
	}

}
