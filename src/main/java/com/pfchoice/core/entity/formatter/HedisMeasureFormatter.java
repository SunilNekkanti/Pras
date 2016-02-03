package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.HedisMeasure;
import com.pfchoice.core.service.HedisMeasureService;

@Component
public class HedisMeasureFormatter implements  Formatter<HedisMeasure> {

     @Autowired
     private HedisMeasureService hedisMeasureService;
     //Some service class which can give the HedisMeasure after
     //fetching from Database
 
     @Override
     public String print(HedisMeasure hedisMeasure, Locale arg1) {
           return hedisMeasure.getDescription();
     }
 
     @Override
      public HedisMeasure parse(String id, Locale arg1) throws ParseException {
           return hedisMeasureService.findById(Integer.parseInt(id));
           //Else you can just return a new object by setting some values
           //which you deem fit.
      }
}
