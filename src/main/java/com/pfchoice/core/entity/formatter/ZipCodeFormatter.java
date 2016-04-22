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
	// Some service class which can give the ZipCodeService after
	// fetching from Database

	@Override
	public String print(ZipCode ZipCode, Locale arg1) {
		return ZipCode.getCode().toString();
	}

	@Override
	public ZipCode parse(String id, Locale arg1) throws ParseException {
		return zipCodeService.findById(Integer.parseInt(id));
		// Else you can just return a new object by setting some values
		// which you deem fit.
	}
}
