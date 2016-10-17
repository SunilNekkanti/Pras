package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.RiskReconDao;
import com.pfchoice.core.entity.RiskRecon;
import com.pfchoice.core.service.RiskReconService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class RiskReconServiceImpl implements RiskReconService {

	@Autowired
	private RiskReconDao riskReconDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.RiskReconService#deleteById(java.lang.
	 * Integer)
	 */
	@Override
	public RiskRecon deleteById(final Integer id) {
		return riskReconDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.RiskReconService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public RiskRecon findById(final Integer id) {
		return riskReconDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.RiskReconService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return riskReconDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.RiskReconService#getPage(int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return riskReconDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.RiskReconService#save(com.pfchoice.core.
	 * entity.RiskRecon)
	 */
	@Override
	public RiskRecon save(final RiskRecon bean) {
		return riskReconDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.RiskReconService#update(com.pfchoice.core.
	 * entity.RiskRecon)
	 */
	@Override
	public RiskRecon update(final RiskRecon bean) {
		Updater<RiskRecon> updater = new Updater<>(bean, Updater.UpdateMode.MAX);
		updater.exclude("createdBy");
		updater.exclude("createdDate");
		return riskReconDao.updateByUpdater(updater);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.RiskReconService#claimReportQuery(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Object[]> claimReportQuery(final String tableName, final Integer insId, final Integer prvdrId, final String repGenDate, final String category, final String adminRole, final String rosterCap){
		return riskReconDao.claimReportQuery(tableName, insId, prvdrId, repGenDate, category, adminRole, rosterCap);
	}
}
