package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.EmailTemplate;
import com.pfchoice.core.service.EmailTemplateService;

@Component
public class EmailTemplateFormatter implements Formatter<EmailTemplate> {

	@Autowired
	private EmailTemplateService emailTemplateService;

	/* (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(EmailTemplate county, Locale arg1) {
		return county.getDescription();
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public EmailTemplate parse(String id, Locale arg1) throws ParseException {
		return emailTemplateService.findById(Integer.parseInt(id));
	}
}
