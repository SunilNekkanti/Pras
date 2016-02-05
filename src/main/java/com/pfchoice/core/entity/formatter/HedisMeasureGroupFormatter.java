package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.HedisMeasureGroup;
import com.pfchoice.core.service.HedisMeasureGroupService;

@Component
public class HedisMeasureGroupFormatter implements  Formatter<HedisMeasureGroup> {

     @Autowired
     private HedisMeasureGroupService hedisMeasureGroupService;
     //Some service class which can give the HedisMeasure after
     //fetching from Database
 
     @Override
     public String print(HedisMeasureGroup hedisMeasureGroup, Locale arg1) {
           return hedisMeasureGroup.getDescription();
     }
 
     @Override
      public HedisMeasureGroup parse(String id, Locale arg1) throws ParseException {
           return hedisMeasureGroupService.findById(Integer.parseInt(id));
           //Else you can just return a new object by setting some values
           //which you deem fit.
      }
}
