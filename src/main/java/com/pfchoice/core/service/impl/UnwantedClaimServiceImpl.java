package com.pfchoice.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.UnwantedClaimDao;
import com.pfchoice.core.entity.UnwantedClaim;
import com.pfchoice.core.service.UnwantedClaimService;

/**
 *
 * @author Sarath
 */
@Service
@Transactional
public class UnwantedClaimServiceImpl implements UnwantedClaimService {

	@Autowired
	private UnwantedClaimDao unwantedClaimDao;

	
	public List<UnwantedClaim> getUnwantedClaims(Integer insId, Integer prvdrId, Integer reportMonth, Integer activityMonth, Boolean isUnwanted){
		
		return unwantedClaimDao.getUnwantedClaims(insId, prvdrId, reportMonth, activityMonth,  isUnwanted);
	}
}
