package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.County;
import com.pfchoice.core.service.CountyService;

@Component
public class CountyFormatter implements Formatter<County> {

	@Autowired
	private CountyService countyService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.format.Printer#print(java.lang.Object,
	 * java.util.Locale)
	 */
	@Override
	public String print(County county, Locale arg1) {
		return county.getDescription();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.format.Parser#parse(java.lang.String,
	 * java.util.Locale)
	 */
	@Override
	public County parse(String id, Locale arg1) throws ParseException {
		return countyService.findById(Integer.parseInt(id));
	}
}
