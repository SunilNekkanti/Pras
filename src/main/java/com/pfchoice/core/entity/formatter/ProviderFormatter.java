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
	// Some service class which can give the Provider after
	// fetching from Database

	@Override
	public String print(Provider provider, Locale arg1) {
		return provider.getName();
	}

	@Override
	public Provider parse(String id, Locale arg1) throws ParseException {
		return providerService.findById(Integer.parseInt(id));
		// Else you can just return a new object by setting some values
		// which you deem fit.
	}
}
