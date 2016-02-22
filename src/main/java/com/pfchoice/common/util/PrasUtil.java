/**
 * 
 */
package com.pfchoice.common.util;

import java.util.HashMap;
import java.util.Map;

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
}
