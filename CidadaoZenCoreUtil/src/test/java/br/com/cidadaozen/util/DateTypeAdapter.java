package br.com.cidadaozen.util;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DateTypeAdapter implements JsonSerializer<Date>,
		JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		try {
			return new Date(json.getAsLong());

		} catch (Exception e) {
			try {
				return new SimpleDateFormat("yyyy-mm-dd").parse(json.getAsString());
			} catch (ParseException e1) {
			}
		}
		return null;
	}

	@Override
	public JsonElement serialize(Date src, Type typeOfSrc,
			JsonSerializationContext context) {

		return new JsonPrimitive(src.getTime());
	}
}