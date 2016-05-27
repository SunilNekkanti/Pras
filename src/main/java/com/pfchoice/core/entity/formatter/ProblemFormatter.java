package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.Problem;
import com.pfchoice.core.service.ProblemService;

@Component
public class ProblemFormatter implements Formatter<Problem> {

	@Autowired
	private ProblemService problemService;

	/* (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(Problem problem, Locale arg1) {
		return problem.getDescription() ;
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public Problem parse(String id, Locale arg1) throws ParseException {
		return problemService.findById(Integer.parseInt(id));
	}
}
