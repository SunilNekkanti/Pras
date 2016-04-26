package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.CPTMeasure;
import com.pfchoice.core.service.CPTMeasureService;

@Component
public class CPTMeasureFormatter implements Formatter<CPTMeasure> {

	@Autowired
	private CPTMeasureService cptMeasureService;

	/* (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(CPTMeasure cptMeasure, Locale arg1) {
		return cptMeasure.getDescription();
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public CPTMeasure parse(String id, Locale arg1) throws ParseException {
		return cptMeasureService.findById(Integer.parseInt(id));
	}
}
