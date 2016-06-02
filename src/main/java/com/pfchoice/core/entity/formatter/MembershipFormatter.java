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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.format.Printer#print(java.lang.Object,
	 * java.util.Locale)
	 */
	@Override
	public String print(Membership membership, Locale arg1) {
		return membership.getFirstName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.format.Parser#parse(java.lang.String,
	 * java.util.Locale)
	 */
	@Override
	public Membership parse(String id, Locale arg1) throws ParseException {
		return membershipService.findById(Integer.parseInt(id));
	}
}
