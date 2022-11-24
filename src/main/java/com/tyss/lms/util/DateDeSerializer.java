package com.tyss.lms.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DateDeSerializer extends StdDeserializer<Date> {

    public DateDeSerializer() {
        super(Date.class);
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
        String value = p.readValueAs(String.class);
        try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(value);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Please Enter Valid Date");
        } catch (ParseException e) {
			// TODO Auto-generated catch block
        	 throw new RuntimeException("Please Enter Valid Date");
		}

    }

	

}