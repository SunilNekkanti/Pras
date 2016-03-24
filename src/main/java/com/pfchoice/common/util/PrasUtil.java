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
 *
 */
public class PrasUtil {

	public static Map<String,String> getActiveIndMap() {
		Map<String,String> activeIndMap = new HashMap<String,String>();
		activeIndMap.put("Y","Yes");
		activeIndMap.put("N","No");
		return activeIndMap;
	}
	
	public static String getPricipal(){
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();	
			final String  loginUser = userDetail.getUsername();
			return loginUser;
		  }
	      
		  return null;
		}
	
	public static SortedSet<Integer> getEffectiveYearList() {
		
		SortedSet<Integer> effYearList = new TreeSet<Integer>();
		DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy");
		
		LocalDate currYear = LocalDate.now();
	    LocalDate nextYear = currYear.plus(1, ChronoUnit.YEARS );
	    LocalDate prevYear  = currYear.minus(1, ChronoUnit.YEARS );
	    
	    effYearList.add(Integer.parseInt( prevYear.format(formatter)));
	    effYearList.add(Integer.parseInt(currYear.format(formatter)));
	    effYearList.add(Integer.parseInt(nextYear.format(formatter)));
	
		return effYearList;
	}
	
	
public static SortedSet<Integer> getHedisEffectiveYearList() {
		
		SortedSet<Integer> effYearList = new TreeSet<Integer>();
		DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy");
		
		LocalDate currYear = LocalDate.now();
	    LocalDate nextYear = currYear.plus(1, ChronoUnit.YEARS );
	    
	    effYearList.add(Integer.parseInt(currYear.format(formatter)));
	    effYearList.add(Integer.parseInt(nextYear.format(formatter)));
	
		return effYearList;
	}
}
