/**
 * 
 */
package com.pfchoice.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author sarath
 * @param <T>
 *
 */
public class PrasUtil<T> {
	
	
	/**
	 * 
	 */
	private PrasUtil() {
		
	}

	/**
	 * 
	 * @return
	 */
	public static Map<String, String> getActiveIndMap() {
		Map<String, String> activeIndMap = new HashMap<>();
		activeIndMap.put("Y", "Yes");
		activeIndMap.put("N", "No");
		return activeIndMap;
	}

	/**
	 * 
	 * @return
	 */
	public static String getPricipal() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			return userDetail.getUsername();
		}

		return null;
	}

	/**
	 * 
	 * @return
	 */
	public static SortedSet<Integer> getEffectiveYearList() {

		SortedSet<Integer> effYearList = new TreeSet<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");

		LocalDate currYear = LocalDate.now();
		LocalDate nextYear = currYear.plus(1, ChronoUnit.YEARS);
		LocalDate prevYear = currYear.minus(1, ChronoUnit.YEARS);

		effYearList.add(Integer.parseInt(prevYear.format(formatter)));
		effYearList.add(Integer.parseInt(currYear.format(formatter)));
		effYearList.add(Integer.parseInt(nextYear.format(formatter)));

		return effYearList;
	}

	/**
	 * 
	 * @return
	 */
	public static SortedSet<Integer> getHedisEffectiveYearList() {

		SortedSet<Integer> effYearList = new TreeSet<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");

		LocalDate currYear = LocalDate.now();
		LocalDate nextYear = currYear.plus(1, ChronoUnit.YEARS);

		effYearList.add(Integer.parseInt(currYear.format(formatter)));
		effYearList.add(Integer.parseInt(nextYear.format(formatter)));

		return effYearList;
	}
	
}
