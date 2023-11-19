package com.example.demo.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            String dateString = p.getValueAsString();
            if (dateString != null && !dateString.isEmpty()) {
                return LocalDateTime.parse(dateString, formatter);
            }
        } catch (Exception e) {
            // Lidar com a exceção ou imprimir para depuração
            e.printStackTrace();
        }
        return null; // Retorna null se a data não puder ser parseada
    }

}