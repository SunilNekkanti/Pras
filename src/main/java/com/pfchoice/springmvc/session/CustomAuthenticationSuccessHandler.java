package com.pfchoice.springmvc.session;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.common.util.TileDefinitions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
		implements AuthenticationSuccessHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {

		HttpSession session = httpServletRequest.getSession(false);
		String redirectUrl = null;
		if (session != null) {
			redirectUrl = (String) session.getAttribute("LAST_PAGE");
			System.out.println("session != null redirectUrl "+redirectUrl);
		}
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		session.setAttribute(SystemDefaultProperties.ID, authUser.getUsername());
		if (authentication.getAuthorities() != null) {
			List<GrantedAuthority> adminauthorities = authentication.getAuthorities().stream()
					.filter(grantedAuthority -> "ROLE_ADMIN".equals(grantedAuthority.getAuthority()))
					.collect(Collectors.toList());
			if (!adminauthorities.isEmpty()) {
				session.setAttribute("userpath", "admin");
			} else {
				List<GrantedAuthority> userauthorities = authentication.getAuthorities().stream()
						.filter(grantedAuthority -> "ROLE_USER".equals(grantedAuthority.getAuthority()))
						.collect(Collectors.toList());

				if (!userauthorities.isEmpty()) {
					session.setAttribute("userpath", "user");
				}
			}
		}
		session.setMaxInactiveInterval(30 * 60);
		// set our response to OK status
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);

		// since we have created our custom success handler, its up to us to
		// where
		// we will redirect the user after successfully login
		if (redirectUrl != null && !redirectUrl.endsWith("/") && !redirectUrl.endsWith("/index")) {
			httpServletResponse.sendRedirect(redirectUrl);
		} else {
			httpServletResponse.sendRedirect(TileDefinitions.HOME.toString());
		}

	}
}