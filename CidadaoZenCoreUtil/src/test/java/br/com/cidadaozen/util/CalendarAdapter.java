package br.com.cidadaozen.util;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CalendarAdapter implements JsonSerializer<Calendar>,
		JsonDeserializer<Calendar> {

	@Override
	public Calendar deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(json.getAsLong()));
		return cal;
	}

	@Override
	public JsonElement serialize(Calendar src, Type typeOfSrc,
			JsonSerializationContext context) {
		Calendar c = Calendar.getInstance();
		c.setTime(src.getTime());
		return null;
	}

}