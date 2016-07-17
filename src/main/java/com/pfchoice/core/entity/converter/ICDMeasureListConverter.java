package com.pfchoice.core.entity.converter;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.AttributeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pfchoice.core.dao.ICDMeasureDao;

@SuppressWarnings("rawtypes")
@Component
public class ICDMeasureListConverter implements AttributeConverter<List, String> {

	private static final String SEPARATOR = ",";

	private static ICDMeasureDao icdMeasureDao;

	@Autowired
	private ICDMeasureDao icdMeasureDao1;

	@PostConstruct
	public void init() {
		ICDMeasureListConverter.icdMeasureDao = icdMeasureDao1;
	}

	@Override
	public String convertToDatabaseColumn(List icdMeasureList) {
		return String.join(",", icdMeasureList.toString());
	}

	@Override
	public List convertToEntityAttribute(String diagnosis) {
		diagnosis = diagnosis.replace(" ", "");
		String[] icdCodes = diagnosis.split(SEPARATOR);

		return icdMeasureDao.findByCodes(icdCodes);
	}
}