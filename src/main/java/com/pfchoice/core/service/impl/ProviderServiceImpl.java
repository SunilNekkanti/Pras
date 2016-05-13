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

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ProviderService#deleteById(java.lang.Integer)
	 */
	@Override
	public Provider deleteById(final Integer id) {
		return providerDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ProviderService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public Provider findById(final Integer id) {
		return providerDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ProviderService#getPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return providerDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ProviderService#save(com.pfchoice.core.entity.Provider)
	 */
	@Override
	public Provider save(final Provider bean) {
		return providerDao.save(bean);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ProviderService#update(com.pfchoice.core.entity.Provider)
	 */
	@Override
	public Provider update(final Provider bean) {
		Updater<Provider> updater = new Updater<>(bean);
		return providerDao.updateByUpdater(updater);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ProviderService#findByInsId(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination findByInsId(final Integer id) {
		return providerDao.findByInsId(id);
	}
	

	/**
	 * @param id
	 * @param npi
	 * @return
	 */
	@Override
	public boolean isUniquePrvdrNPI(Integer id, String npi) {
		Provider prvdr = findByPrvdrNPI(npi);
		return ( prvdr == null || ((id != null) && (prvdr.getId() == id)));
	}
	
	/**
	 * @param npi
	 * @return
	 */
	@Override
	public Provider findByPrvdrNPI(String npi) {
		Provider prvdr = providerDao.findByPrvdrNPI(npi);
		return prvdr;
	}

}
