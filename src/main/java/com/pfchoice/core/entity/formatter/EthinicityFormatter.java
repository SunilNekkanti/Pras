package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.Ethinicity;
import com.pfchoice.core.service.EthinicityService;

@Component
public class EthinicityFormatter implements  Formatter<Ethinicity> {

     @Autowired
     private EthinicityService ethinicityService;
     //Some service class which can give the CountyService after
     //fetching from Database
 
     @Override
     public String print(Ethinicity ethinicity, Locale arg1) {
           return ethinicity.getDescription();
     }
 
     @Override
      public Ethinicity parse(String id, Locale arg1) throws ParseException {
    	 System.out.println("ethinic id "+id);
           return ethinicityService.findById(Byte.parseByte(id));
           //Else you can just return a new object by setting some values
           //which you deem fit.
      }
}
