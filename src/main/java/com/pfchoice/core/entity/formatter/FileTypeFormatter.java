package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.FileType;
import com.pfchoice.core.service.FileTypeService;


@Component
public class FileTypeFormatter implements  Formatter<FileType> {

     @Autowired
     private FileTypeService fileTypeService;
     //Some service class which can give the FileType after
     //fetching from Database
 
     @Override
     public String print(FileType fileType, Locale arg1) {
           return fileType.getDescription();
     }
 
     @Override
      public FileType parse(String id, Locale arg1) throws ParseException {
           return fileTypeService.findById(Integer.parseInt(id));
           //Else you can just return a new object by setting some values
           //which you deem fit.
      }
}
