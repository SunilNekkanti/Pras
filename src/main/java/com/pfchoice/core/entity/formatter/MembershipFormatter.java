package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.Membership;
import com.pfchoice.core.service.MembershipService;

@Component
public class MembershipFormatter implements Formatter<Membership> {

	@Autowired
	private MembershipService membershipService;
	// Some service class which can give the Membership after
	// fetching from Database

	@Override
	public String print(Membership membership, Locale arg1) {
		return membership.getFirstName();
	}

	@Override
	public Membership parse(String id, Locale arg1) throws ParseException {
		return membershipService.findById(Integer.parseInt(id));
		// Else you can just return a new object by setting some values
		// which you deem fit.
	}
}
