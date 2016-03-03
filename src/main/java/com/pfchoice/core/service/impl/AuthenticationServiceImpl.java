package com.pfchoice.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfchoice.core.dao.UserDao;
import com.pfchoice.core.entity.Role;

/**
 *
 * @author Sarath
 */

@Service
@Transactional
public class AuthenticationServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username)
				throws UsernameNotFoundException {

		com.pfchoice.core.entity.User user = userDao.findByLogin(username);
		Set<Role> roles = user.getRoles();
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Role r:roles){
			GrantedAuthority authority = new SimpleGrantedAuthority(r.getRole());
			authorities.add(authority);
		}
		
		UserDetails userDetails = (UserDetails)new User(user.getUsername(), 
		user.getPassword(), authorities);
		return userDetails;
	
	} 
}
