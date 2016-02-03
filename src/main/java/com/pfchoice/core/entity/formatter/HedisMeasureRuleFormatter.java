package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.pfchoice.core.entity.HedisMeasureRule;
import com.pfchoice.core.service.HedisMeasureRuleService;

@Component
public class HedisMeasureRuleFormatter implements  Formatter<HedisMeasureRule> {

     @Autowired
     private HedisMeasureRuleService hedisMeasureRuleService;
     //Some service class which can give the HedisMeasureRule after
     //fetching from Database
 
     @Override
     public String print(HedisMeasureRule hedisMeasureRule, Locale arg1) {
           return hedisMeasureRule.getId().toString();
     }
 
     @Override
      public HedisMeasureRule parse(String id, Locale arg1) throws ParseException {
           return hedisMeasureRuleService.findById(Integer.parseInt(id));
           //Else you can just return a new object by setting some values
           //which you deem fit.
      }
}
