package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.Ethinicity;
import com.pfchoice.core.service.EthinicityService;

@Component
public class EthinicityFormatter implements Formatter<Ethinicity> {

	@Autowired
	private EthinicityService ethinicityService;

	/* (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(Ethinicity ethinicity, Locale arg1) {
		return ethinicity.getDescription();
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public Ethinicity parse(String id, Locale arg1) throws ParseException {
		
		return ethinicityService.findById(Byte.parseByte(id));
		
	}
}
