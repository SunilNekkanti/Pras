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

	@Override
	public Contract deleteById(final Integer id) {
		// Used for transaction test
		return contractDao.deleteById(id);
		// throw new UnsupportedOperationException();
	}

	@Override
	@Transactional(readOnly = true)
	public Contract findById(final Integer id) {
		return contractDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return contractDao.getPage(pageNo, pageSize);
	}

	@Override
	public Contract save(final Contract bean) {
		// Used for transaction test
		return contractDao.save(bean);
		// this.deleteById(1);
		// return null;
	}

	@Override
	public Contract update(final Contract bean) {
		Updater<Contract> updater = new Updater<>(bean);
		return contractDao.updateByUpdater(updater);
	}

	@Override
	public List<Contract> findAllContractsByRefId(final String refString, Integer id) {
		return contractDao.findAllContractsByRefId(refString, id);
	}

	@Override
	public Contract findActiveContractByRefId(final String refString, Integer id) {
		return contractDao.findActiveContractByRefId(refString, id);
	}
}
