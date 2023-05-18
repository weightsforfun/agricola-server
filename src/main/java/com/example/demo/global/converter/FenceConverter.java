package com.example.demo.global.converter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class FenceConverter implements AttributeConverter<String[][],String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(String[][] attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            // 예외 처리
            return null;
        }
    }

    @Override
    public String[][] convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, String[][].class);
        } catch (JsonProcessingException e) {
            // 예외 처리
            return null;
        }
    }
}
