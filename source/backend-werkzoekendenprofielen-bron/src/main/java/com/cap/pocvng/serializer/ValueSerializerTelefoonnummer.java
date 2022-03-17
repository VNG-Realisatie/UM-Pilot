package com.cap.pocvng.serializer;

import com.cap.pocvng.entity.Telefoonnummer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ValueSerializerTelefoonnummer extends JsonSerializer<Telefoonnummer> {

    @Override
    public void serialize(Telefoonnummer telefoonnummer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(telefoonnummer.getTelefoonnummer());
    }
}
