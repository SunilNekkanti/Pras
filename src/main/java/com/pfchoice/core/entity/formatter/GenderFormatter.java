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

	/* (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(Gender gender, Locale arg1) {
		return gender.getDescription();
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public Gender parse(String id, Locale arg1) throws ParseException {
		return genderService.findById(Byte.parseByte(id));
	}
}
