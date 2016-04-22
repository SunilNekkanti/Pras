package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.State;
import com.pfchoice.core.service.StateService;

@Component
public class StateFormatter implements Formatter<State> {

	@Autowired
	private StateService stateService;
	// Some service class which can give the StateService after
	// fetching from Database

	@Override
	public String print(State state, Locale arg1) {
		return state.getDescription();
	}

	@Override
	public State parse(String id, Locale arg1) throws ParseException {
		return stateService.findById(Integer.parseInt(id));
		// Else you can just return a new object by setting some values
		// which you deem fit.
	}
}
