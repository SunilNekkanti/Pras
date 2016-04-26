package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.HedisMeasureGroup;
import com.pfchoice.core.service.HedisMeasureGroupService;

@Component
public class HedisMeasureGroupFormatter implements Formatter<HedisMeasureGroup> {

	@Autowired
	private HedisMeasureGroupService hedisMeasureGroupService;

	/* (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(HedisMeasureGroup hedisMeasureGroup, Locale arg1) {
		return hedisMeasureGroup.getDescription();
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public HedisMeasureGroup parse(String id, Locale arg1) throws ParseException {
		return hedisMeasureGroupService.findById(Integer.parseInt(id));
	}
}
