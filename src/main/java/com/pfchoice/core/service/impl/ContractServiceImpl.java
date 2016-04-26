package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.ContractDao;
import com.pfchoice.core.entity.Contract;
import com.pfchoice.core.service.ContractService;

/**
 *
 * @author Mohanasundharam
 */
@Service
@Transactional
public class ContractServiceImpl implements ContractService {

	@Autowired
	private ContractDao contractDao;

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ContractService#deleteById(java.lang.Integer)
	 */
	@Override
	public Contract deleteById(final Integer id) {
		return contractDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ContractService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public Contract findById(final Integer id) {
		return contractDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ContractService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return contractDao.getPage(pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ContractService#save(com.pfchoice.core.entity.Contract)
	 */
	@Override
	public Contract save(final Contract bean) {
		return contractDao.save(bean);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ContractService#update(com.pfchoice.core.entity.Contract)
	 */
	@Override
	public Contract update(final Contract bean) {
		Updater<Contract> updater = new Updater<>(bean);
		return contractDao.updateByUpdater(updater);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ContractService#findAllContractsByRefId(java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<Contract> findAllContractsByRefId(final String refString, Integer id) {
		return contractDao.findAllContractsByRefId(refString, id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.ContractService#findActiveContractByRefId(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Contract findActiveContractByRefId(final String refString, Integer id) {
		return contractDao.findActiveContractByRefId(refString, id);
	}
}
