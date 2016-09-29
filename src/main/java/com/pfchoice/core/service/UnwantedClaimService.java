package com.pfchoice.core.service;

import java.util.List;

import com.pfchoice.core.entity.UnwantedClaim;

/**
 *
 * @author sarath
 */
public interface UnwantedClaimService {

	/**
	 * @param insId
	 * @param prvdrId
	 * @param reportMonth
	 * @param activityMonth
	 */
	List<UnwantedClaim> getUnwantedClaims(Integer insId, Integer prvdrId, Integer reportMonth, Integer activityMonth);

}
