package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.PlanType;
import com.pfchoice.core.service.PlanTypeService;

@Component
public class PlanTypeFormatter implements Formatter<PlanType> {

	@Autowired
	private PlanTypeService planTypeService;

	/* (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(PlanType planType, Locale arg1) {
		return planType.getCode();
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public PlanType parse(String id, Locale arg1) throws ParseException {
		return planTypeService.findById(Integer.parseInt(id));
	}
}
