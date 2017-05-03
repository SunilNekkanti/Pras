/**
 * 
 */
package com.pfchoice.springmvc.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sarath
 *
 */
@Component
public class LastPageInterceptor extends HandlerInterceptorAdapter {

	public static final String LAST_PAGE = "LAST_PAGE";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
																					// 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		return true;
	};

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
		String uri = request.getHeader("Referer");
		System.out.println("Uri"+uri);
		request.getSession().setAttribute(LAST_PAGE, uri);
	};

	public static String getLastPage() {
		return LAST_PAGE;
	}

}