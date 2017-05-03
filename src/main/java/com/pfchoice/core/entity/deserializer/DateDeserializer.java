package com.pfchoice.core.entity.deserializer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class DateDeserializer implements JsonDeserializer<Date> {
	private static final String[] DATE_FORMATS = new String[] {
	        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
	        "yyyy-MM-dd'T'HH:mm:ssZZZZZ",
	        "yyyy-MM-dd",
	        "MM-dd-yyyy",
	        "MM/dd/yyyy"
	};
    @Override
    public Date deserialize(JsonElement jsonElement, Type typeOF,
            JsonDeserializationContext context) throws JsonParseException {
        for (String format : DATE_FORMATS) {
            try {
                return new SimpleDateFormat(format, Locale.US).parse(jsonElement.getAsString());
            } catch (ParseException e) {
            }
        }
        throw new JsonParseException("Unparseable date: \"" + jsonElement.getAsString()
                + "\". Supported formats: " + Arrays.toString(DATE_FORMATS));
    }
}
