package com.pfchoice.springmvc.interceptor;

import com.google.gson.Gson;
import com.pfchoice.common.CommonLogContent;
import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.core.entity.User;
import com.pfchoice.core.service.UserService;

import java.io.IOException;
import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ml.rugal.sshcommon.springmvc.util.Message;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * A authentication interceptor than authenticate any matched request by some
 * credential. Store username and credential in request header.
 * <p>
 * Please implement your own authentication class to ensure the right method you
 * need to verify access.
 * <p>
 * Useful when implementing Restful API.
 * <p>
 * 
 * @author Sarath
 * @since 0.6
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationInterceptor.class.getName());

	private final Gson gson = new Gson();

	@Autowired
	UserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String id = (String) request.getSession().getAttribute(SystemDefaultProperties.ID);
		String credential = (String) request.getSession().getAttribute(SystemDefaultProperties.CREDENTIAL);
		boolean status = true;
		LOG.info(MessageFormat.format(CommonLogContent.USER_TRY_ACCESS, id, request.getRequestURI(),
				request.getRemoteAddr()));

		if (!"/Pras/".equals(request.getRequestURI()) && !"/Pras/loginform".equals(request.getRequestURI())
				&& !"/Pras/index".equals(request.getRequestURI()) && !isAuthenticatedUser(id, credential)) {
			status = false;
			forbiddenResponse(response);
			LOG.warn(MessageFormat.format(CommonLogContent.USER_ACCESS_FAILED, id, credential, request.getRequestURI(),
					request.getRemoteAddr()));
			response.sendRedirect("/Pras/");

		}
		return status;
	}

	/**
	 * This method is just for generating a response with forbidden content.<BR>
	 * May throw IOException inside because unable to get response body writer,
	 * but this version will shelter it.
	 *
	 *
	 * @param response
	 *            The response corresponding to the request.
	 */
	private void forbiddenResponse(HttpServletResponse response) {
		try {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.getWriter().print(gson.toJson(Message.failMessage(CommonMessageContent.ACCESS_FORBIDDEN)));

		} catch (IOException e) {
			LOG.error("Unable to get response writer", e);
		}

	}

	/**
	 * This method used put authentication. If you need to check with database,
	 * please modify code.
	 *
	 * @param username
	 *            user ID
	 * @param credential
	 *            user password
	 *
	 * @return true if this user and credential meet requirement, otherwise
	 *         return false
	 */
	private boolean isAuthenticatedUser(String username, String credential) {
		User dbUser = userService.findByLogin(username);
		final String login = (dbUser != null) ? dbUser.getUsername() : "tst";
		final String password = (dbUser != null) ? dbUser.getPassword() : "";
		return StringUtils.equals(username, login) && StringUtils.equals(credential, password);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOG.info(MessageFormat.format(CommonLogContent.USER_ACCESS_SUCCEEDED,
				request.getSession().getAttribute(SystemDefaultProperties.ID), request.getRequestURI(),
				request.getRemoteAddr()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LOG.info("afterCompletion");
	}

}
