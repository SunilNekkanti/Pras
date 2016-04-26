package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.service.InsuranceService;

@Component
public class InsuranceFormatter implements Formatter<Insurance> {

	@Autowired
	private InsuranceService insuranceService;

	/* (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(Insurance insurance, Locale arg1) {
		return insurance.getName();
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public Insurance parse(String id, Locale arg1) throws ParseException {
		return insuranceService.findById(Integer.parseInt(id));
	}
}
