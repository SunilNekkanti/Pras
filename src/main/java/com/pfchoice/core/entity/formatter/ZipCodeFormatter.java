package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.ZipCode;
import com.pfchoice.core.service.ZipCodeService;

@Component
public class ZipCodeFormatter implements Formatter<ZipCode> {

	@Autowired
	private ZipCodeService zipCodeService;

	/* (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(ZipCode ZipCode, Locale arg1) {
		return ZipCode.getCode().toString();
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public ZipCode parse(String id, Locale arg1) throws ParseException {
		return zipCodeService.findById(Integer.parseInt(id));
	}
}
