package com.pfchoice.springmvc.web.exception;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class PFChoiceAccessDeniedHandler implements AccessDeniedHandler {

	private String errorPage;

	/**
	 * 
	 */
	public PFChoiceAccessDeniedHandler() {
		super();
	}

	/**
	 * @param errorPage
	 */
	public PFChoiceAccessDeniedHandler(String errorPage) {
		super();
		this.errorPage = errorPage;
	}

	/**
	 * @return
	 */
	public String getErrorPage() {
		return errorPage;
	}

	/**
	 * @param errorPage
	 */
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.access.AccessDeniedHandler#handle(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.access.AccessDeniedException)
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.sendRedirect(errorPage);

	}

}