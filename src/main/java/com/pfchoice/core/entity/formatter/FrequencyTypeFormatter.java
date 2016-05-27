package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.FrequencyType;
import com.pfchoice.core.service.FrequencyTypeService;

@Component
public class FrequencyTypeFormatter implements Formatter<FrequencyType> {

	@Autowired
	private FrequencyTypeService frequencyTypeService;

	/* (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(FrequencyType frequencyType, Locale arg1) {
		return frequencyType.getDescription() ;
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public FrequencyType parse(String id, Locale arg1) throws ParseException {
		return frequencyTypeService.findById(Integer.parseInt(id));
	}
}
