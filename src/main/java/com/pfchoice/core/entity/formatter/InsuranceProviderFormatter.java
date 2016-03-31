package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.InsuranceProvider;
import com.pfchoice.core.service.InsuranceProviderService;

@Component
public class InsuranceProviderFormatter implements  Formatter<InsuranceProvider> {

     @Autowired
     private InsuranceProviderService insuranceProviderService;
     //Some service class which can give the Insurance after
     //fetching from Database
 
     @Override
     public String print(InsuranceProvider insuranceProvider, Locale arg1) {
           return insuranceProvider.getId().toString();
     }
 
     @Override
      public InsuranceProvider parse(String id, Locale arg1) throws ParseException {
           return insuranceProviderService.findById(Integer.parseInt(id));
           //Else you can just return a new object by setting some values
           //which you deem fit.
      }
}
