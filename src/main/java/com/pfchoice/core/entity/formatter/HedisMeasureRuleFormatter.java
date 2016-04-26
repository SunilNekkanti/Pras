package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.HedisMeasureRule;
import com.pfchoice.core.service.HedisMeasureRuleService;

@Component
public class HedisMeasureRuleFormatter implements Formatter<HedisMeasureRule> {

	@Autowired
	private HedisMeasureRuleService hedisMeasureRuleService;

	/* (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(HedisMeasureRule hedisMeasureRule, Locale arg1) {
		return hedisMeasureRule.getId().toString();
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public HedisMeasureRule parse(String id, Locale arg1) throws ParseException {
		return hedisMeasureRuleService.findById(Integer.parseInt(id));
	}
}
