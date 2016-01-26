package com.pfchoice.common.util;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
* A workaround for avoiding circular reference issue in Json object
* 
* Converting Entity objects to Json using GsonBuilder and then 
* removing the circular reference and converting back to Entity beans
* 
* @author Sarath
*/
public class JsonConverter {
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getJsonObject(List<T> bean){
		
		Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
	     String json = gson.toJson(bean);
	     List<T> listBean= gson.fromJson(json, List.class);
	     return  listBean;
	}
	
	public static <T> T getJsonObject(T bean){
		
		Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
	     String json = gson.toJson(bean);
	     T bean1= gson.fromJson(json, bean.getClass());
	     return  bean1;
	}

}
