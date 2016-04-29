package com.pfchoice.springmvc.session;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.common.util.TileDefinitions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {

		HttpSession session = httpServletRequest.getSession();
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		session.setAttribute(SystemDefaultProperties.ID, authUser.getUsername());
		if (authentication.getAuthorities() != null) {
			List<GrantedAuthority> adminauthorities = authentication.getAuthorities().stream()
					.filter(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))
					.collect(Collectors.toList());
			if (adminauthorities.size() > 0) {
				session.setAttribute("userpath", "admin");
			} else {
				List<GrantedAuthority> userauthorities = authentication.getAuthorities().stream()
						.filter(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"))
						.collect(Collectors.toList());

				if (userauthorities.size() > 0) {
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
		httpServletResponse.sendRedirect(TileDefinitions.HOME.toString());
	}
}