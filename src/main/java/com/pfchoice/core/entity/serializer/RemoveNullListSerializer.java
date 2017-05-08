package com.pfchoice.core.entity.serializer;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class RemoveNullListSerializer<T> implements JsonSerializer<Collection<T>> {

    @Override
    public JsonElement serialize(Collection<T> src, Type typeOfSrc,
            JsonSerializationContext context) {

        // remove all null values
        src.removeAll(Collections.singleton(null));
        if (src == null || src.isEmpty()) // exclusion is made here
            return null;
       
        // create json Result
        JsonArray result = new JsonArray();
        for(T item : src){
            result.add(context.serialize(item));
        }
        return result;
    }
}
