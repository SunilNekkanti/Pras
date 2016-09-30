package com.pfchoice.core.dao;

import java.util.List;

import com.pfchoice.core.entity.UnwantedClaim;

/**
 *
 * @author Sarath
 */
public interface UnwantedClaimDao {

	/**
	 * @param insId
	 * @param prvdrId
	 * @param reportMonth
	 * @param activityMonth
	 * @return
	 */
	List<UnwantedClaim> getUnwantedClaims(Integer insId, Integer prvdrId, Integer reportMonth, Integer activityMonth, Boolean isUnwanted);
	
}
