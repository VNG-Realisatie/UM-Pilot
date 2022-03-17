package com.cap.pocvng.serializer;

import com.cap.pocvng.entity.Emailadres;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ValueSerializerEmail extends JsonSerializer<Emailadres> {
    @Override
    public void serialize(Emailadres emailadres, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(emailadres.getEmailadres());
    }
}
