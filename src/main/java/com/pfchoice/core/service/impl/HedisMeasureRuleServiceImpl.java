package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.HedisMeasureRuleDao;
import com.pfchoice.core.entity.HedisMeasureRule;
import com.pfchoice.core.service.HedisMeasureRuleService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class HedisMeasureRuleServiceImpl implements HedisMeasureRuleService {

	@Autowired
	private HedisMeasureRuleDao hedisMeasureRuleDao;

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.HedisMeasureRuleService#deleteById(java.lang.Integer)
	 */
	@Override
	public HedisMeasureRule deleteById(final Integer id) {
		return hedisMeasureRuleDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.HedisMeasureRuleService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public HedisMeasureRule findById(final Integer id) {
		return hedisMeasureRuleDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.HedisMeasureRuleService#getPage(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return hedisMeasureRuleDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.HedisMeasureRuleService#getPage(int, int, java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir, final int insId, final int effYear) {
		return hedisMeasureRuleDao.getPage(pageNo, pageSize, sSearch, sort, sortdir, insId, effYear);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.HedisMeasureRuleService#save(com.pfchoice.core.entity.HedisMeasureRule)
	 */
	@Override
	public HedisMeasureRule save(final HedisMeasureRule bean) {
		return hedisMeasureRuleDao.save(bean);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.HedisMeasureRuleService#update(com.pfchoice.core.entity.HedisMeasureRule)
	 */
	@Override
	public HedisMeasureRule update(final HedisMeasureRule bean) {
		Updater<HedisMeasureRule> updater = new Updater<>(bean);
		return hedisMeasureRuleDao.updateByUpdater(updater);
	}

	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.HedisMeasureRuleService#findAllByInsId(java.lang.Integer)
	 */
	@Override
	public List<HedisMeasureRule> findAllByInsId(final Integer insId) {
		return hedisMeasureRuleDao.findAllByInsId(insId);
	}
	
	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.HedisMeasureRuleService#findAllByInsId(java.lang.Integer)
	 */
	@Override
	public List<HedisMeasureRule> findAllByInsAndPbm(final Integer insId) {
		return hedisMeasureRuleDao.findAllByInsAndPbm(insId);
	}

}
