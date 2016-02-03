package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.service.InsuranceService;

@Component
public class InsuranceFormatter implements  Formatter<Insurance> {

     @Autowired
     private InsuranceService insuranceService;
     //Some service class which can give the Insurance after
     //fetching from Database
 
     @Override
     public String print(Insurance insurance, Locale arg1) {
           return insurance.getName();
     }
 
     @Override
      public Insurance parse(String id, Locale arg1) throws ParseException {
           return insuranceService.findById(Integer.parseInt(id));
           //Else you can just return a new object by setting some values
           //which you deem fit.
      }
}
