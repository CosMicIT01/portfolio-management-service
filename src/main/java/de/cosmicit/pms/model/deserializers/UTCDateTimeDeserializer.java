package de.cosmicit.pms.model.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class UTCDateTimeDeserializer extends JsonDeserializer<DateTime> {

    @Override
    public DateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonToken token = p.getCurrentToken();
        if (token == JsonToken.VALUE_STRING) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
//            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
            DateTime dateTime = formatter.withZone(DateTimeZone.UTC)
                    .withChronology(ISOChronology.getInstanceUTC()).parseDateTime(p.getText().trim());
//            String dateTimeString = p.getText().trim();
//            return DateTime.parse(dateTimeString, formatter);
            return dateTime;
        }
        return null;
    }
}
