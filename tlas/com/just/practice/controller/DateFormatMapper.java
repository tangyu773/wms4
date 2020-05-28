package com.just.practice.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

public class DateFormatMapper extends ObjectMapper {

	private String format = "yyyy-MM-dd HH:mm:ss";

	public DateFormatMapper() {
		CustomSerializerFactory factory = new CustomSerializerFactory();

		factory.addGenericMapping(Date.class, new JsonSerializer<Date>() {
			@Override
			public void serialize(Date value, JsonGenerator jsonGenerator,
					SerializerProvider serializerProvider) throws IOException,
					JsonProcessingException {
				if (value != null) {
					SimpleDateFormat sdf = new SimpleDateFormat(format);
					jsonGenerator.writeString(sdf.format(value));
				}
			}
		});
		this.setSerializerFactory(factory);
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
