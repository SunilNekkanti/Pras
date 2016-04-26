package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.ICDMeasure;
import com.pfchoice.core.service.ICDMeasureService;

@Component
public class ICDMeasureFormatter implements Formatter<ICDMeasure> {

	@Autowired
	private ICDMeasureService icdMeasureService;

	/* (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(ICDMeasure icdMeasure, Locale arg1) {
		return icdMeasure.getDescription();
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public ICDMeasure parse(String id, Locale arg1) throws ParseException {
		return icdMeasureService.findById(Integer.parseInt(id));
	}
}
