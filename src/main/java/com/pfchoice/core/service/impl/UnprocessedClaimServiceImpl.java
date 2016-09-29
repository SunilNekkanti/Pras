package com.pfchoice.core.service.impl;

import ml.rugal.sshcommon.page.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.UnprocessedClaimDao;
import com.pfchoice.core.service.UnprocessedClaimService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class UnprocessedClaimServiceImpl implements UnprocessedClaimService {

	@Autowired
	private UnprocessedClaimDao unprocessedClaimDao;


	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipClaimService#getPage(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(final int pageNo, final int pageSize) {
		return unprocessedClaimDao.getPage(pageNo, pageSize);
	}


	/* (non-Javadoc)
	 * @see com.pfchoice.core.service.MembershipClaimService#loadDataCSV2Table(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Integer loadDataCSV2Table(final Integer fileId, final String insuranceCode, final String tableNames, final Integer insId, final Integer  reportMonth) {
		return unprocessedClaimDao.loadDataCSV2Table(fileId, insuranceCode, tableNames, insId, reportMonth);
	}

}
