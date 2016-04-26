package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.Role;
import com.pfchoice.core.service.RoleService;

@Component
public class RoleFormatter implements Formatter<Role> {

	/*
	 * Some service class which can give the RoleService after
	 * fetching from Database
	 */
	@Autowired
	private RoleService roleService;

	/* (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(Role role, Locale arg1) {
		return role.getRole();
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public Role parse(String id, Locale arg1) throws ParseException {
		
		return roleService.findById(Integer.parseInt(id));
	}
}
