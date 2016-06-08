package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.MembershipHedisMeasureDao;
import com.pfchoice.core.entity.MembershipHedisMeasure;
import com.pfchoice.core.service.MembershipHedisMeasureService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class MembershipHedisMeasureServiceImpl implements MembershipHedisMeasureService {

	@Autowired
	private MembershipHedisMeasureDao membershipHedisMeasureDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipHedisMeasureService#deleteById(java.
	 * lang.Integer)
	 */
	@Override
	public MembershipHedisMeasure deleteById(final Integer id) {
		return membershipHedisMeasureDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipHedisMeasureService#findById(java.
	 * lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public MembershipHedisMeasure findById(final Integer id) {
		return membershipHedisMeasureDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipHedisMeasureService#getPage(int,
	 * int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final String sort,
			final String sortdir) {
		return membershipHedisMeasureDao.getPage(pageNo, pageSize, sSearch, sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipHedisMeasureService#getPage(int,
	 * int, java.lang.String, int, int, int, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize, final String sSearch, final int sSearchIns,
			final int sSearchPrvdr, final int sSearchHedisCode, final String sort, final String sortdir) {
		return membershipHedisMeasureDao.getPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr, sSearchHedisCode,
				sort, sortdir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipHedisMeasureService#save(com.pfchoice
	 * .core.entity.MembershipHedisMeasure)
	 */
	@Override
	public MembershipHedisMeasure save(final MembershipHedisMeasure bean) {
		return membershipHedisMeasureDao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipHedisMeasureService#update(com.
	 * pfchoice.core.entity.MembershipHedisMeasure)
	 */
	@Override
	public MembershipHedisMeasure update(final MembershipHedisMeasure bean) {
		Updater<MembershipHedisMeasure> updater = new Updater<>(bean);
		return membershipHedisMeasureDao.updateByUpdater(updater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pfchoice.core.service.MembershipHedisMeasureService#
	 * findByMbrIdAndRuleId(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Pagination findByMbrIdAndRuleId(final Integer mbrId, final Integer ruleId) {
		return membershipHedisMeasureDao.findByMbrIdAndRuleId(mbrId, ruleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pfchoice.core.service.MembershipHedisMeasureService#loadData(java.
	 * lang.Integer)
	 */
	@Override
	public Integer loadData(final Integer fileId, final String tableName) {
		return membershipHedisMeasureDao.loadData(fileId, tableName);
	}
}
