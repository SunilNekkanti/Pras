/**
 * 
 */
package com.pfchoice.common.util;

import java.util.HashMap;
import java.util.Map;

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
}
