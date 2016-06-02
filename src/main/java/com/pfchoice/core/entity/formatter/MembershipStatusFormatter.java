package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.MembershipStatus;
import com.pfchoice.core.service.MembershipStatusService;

@Component
public class MembershipStatusFormatter implements Formatter<MembershipStatus> {

	@Autowired
	private MembershipStatusService membershipStatusService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.format.Printer#print(java.lang.Object,
	 * java.util.Locale)
	 */
	@Override
	public String print(MembershipStatus membershipStatus, Locale arg1) {
		return membershipStatus.getDescription();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.format.Parser#parse(java.lang.String,
	 * java.util.Locale)
	 */
	@Override
	public MembershipStatus parse(String id, Locale arg1) throws ParseException {
		return membershipStatusService.findById(Byte.parseByte(id));
	}
}
