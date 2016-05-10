package com.pfchoice.core.entity.serializer;

import java.lang.reflect.Type;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pfchoice.core.entity.MembershipFollowup;

public class MembershipFollowupSerializer implements JsonSerializer<MembershipFollowup> {


    @Override
    public JsonElement serialize(MembershipFollowup mbrFollowup, Type type, JsonSerializationContext context) {
        JsonObject root = new JsonObject();
        root.addProperty("id", mbrFollowup.getId());
        root.addProperty("mbr", mbrFollowup.getMbr().toString());
        root.addProperty("followupType", mbrFollowup.getFollowupType().toString());
        root.addProperty("followupDetails", mbrFollowup.getFollowupDetails());
        root.addProperty("createdDate", mbrFollowup.getCreatedDate().toString());
        root.addProperty("createdBy", mbrFollowup.getCreatedBy());
        root.addProperty("activeInd", mbrFollowup.getActiveInd());

        return root;
    }

}