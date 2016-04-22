package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.service.GenderService;

@Component
public class GenderFormatter implements Formatter<Gender> {

	@Autowired
	private GenderService genderService;
	// Some service class which can give the MembershipStatus after
	// fetching from Database

	@Override
	public String print(Gender gender, Locale arg1) {
		return gender.getDescription();
	}

	@Override
	public Gender parse(String id, Locale arg1) throws ParseException {
		return genderService.findById(Byte.parseByte(id));
		// Else you can just return a new object by setting some values
		// which you deem fit.
	}
}
