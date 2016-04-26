package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.FileType;
import com.pfchoice.core.service.FileTypeService;

@Component
public class FileTypeFormatter implements Formatter<FileType> {

	@Autowired
	private FileTypeService fileTypeService;

	/* (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(FileType fileType, Locale arg1) {
		return fileType.getDescription();
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public FileType parse(String id, Locale arg1) throws ParseException {
		return fileTypeService.findById(Integer.parseInt(id));
	}
}
