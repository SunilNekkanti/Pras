package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.CPTMeasure;
import com.pfchoice.core.service.CPTMeasureService;

@Component
public class CPTMeasureFormatter implements  Formatter<CPTMeasure> {

     @Autowired
     private CPTMeasureService cptMeasureService;
     //Some service class which can give the CPTMeasure after
     //fetching from Database
 
     @Override
     public String print(CPTMeasure cptMeasure, Locale arg1) {
           return cptMeasure.getDescription();
     }
 
     @Override
      public CPTMeasure parse(String id, Locale arg1) throws ParseException {
           return cptMeasureService.findById(Integer.parseInt(id));
           //Else you can just return a new object by setting some values
           //which you deem fit.
      }
}
