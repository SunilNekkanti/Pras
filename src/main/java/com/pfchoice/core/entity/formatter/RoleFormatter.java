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

	@Autowired
	private RoleService roleService;
	// Some service class which can give the RoleService after
	// fetching from Database

	@Override
	public String print(Role role, Locale arg1) {
		return role.getRole();
	}

	@Override
	public Role parse(String id, Locale arg1) throws ParseException {
		return roleService.findById(Integer.parseInt(id));
		// Else you can just return a new object by setting some values
		// which you deem fit.
	}
}
