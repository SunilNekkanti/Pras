package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.service.ProviderService;

@Component
public class ProviderFormatter implements Formatter<Provider> {

	@Autowired
	private ProviderService providerService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.format.Printer#print(java.lang.Object,
	 * java.util.Locale)
	 */
	@Override
	public String print(Provider provider, Locale arg1) {
		return provider.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.format.Parser#parse(java.lang.String,
	 * java.util.Locale)
	 */
	@Override
	public Provider parse(String id, Locale arg1) throws ParseException {
		return providerService.findById(Integer.parseInt(id));
	}
}
