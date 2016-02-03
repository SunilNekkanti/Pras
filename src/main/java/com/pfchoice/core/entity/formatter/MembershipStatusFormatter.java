package com.pfchoice.core.entity.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;


import com.pfchoice.core.entity.MembershipStatus;
import com.pfchoice.core.service.MembershipStatusService;

@Component
public class MembershipStatusFormatter implements  Formatter<MembershipStatus> {

     @Autowired
     private MembershipStatusService membershipStatusService;
     //Some service class which can give the MembershipStatus after
     //fetching from Database
 
     @Override
     public String print(MembershipStatus membershipStatus, Locale arg1) {
           return membershipStatus.getDescription();
     }
 
     @Override
      public MembershipStatus parse(String id, Locale arg1) throws ParseException {
           return membershipStatusService.findById(Byte.parseByte(id));
           //Else you can just return a new object by setting some values
           //which you deem fit.
      }
}
