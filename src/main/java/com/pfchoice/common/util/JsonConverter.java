package com.pfchoice.common.util;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pfchoice.core.entity.MembershipFollowup;
import com.pfchoice.core.entity.serializer.MembershipFollowupSerializer;

/**
 * A workaround for avoiding circular reference issue in Json object
 * 
 * Converting Entity objects to Json using GsonBuilder and then removing the
 * circular reference and converting back to Entity beans
 * 
 * @author Sarath
 */
public class JsonConverter {

	private JsonConverter() {

	}

	/**
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getJsonObject(List<T> bean) {

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(MembershipFollowup.class, new MembershipFollowupSerializer()).create();
		String json = gson.toJson(bean);
		return gson.fromJson(json, List.class);
	}

	/**
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getJsonObject(T bean) {

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(MembershipFollowup.class, new MembershipFollowupSerializer()).create();
		String json = gson.toJson(bean);
		return (T) gson.fromJson(json, bean.getClass());
	}

}
