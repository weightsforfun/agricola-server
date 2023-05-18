package com.example.demo.global.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.AttributeConverter;

public class BuildingToStringConverter implements AttributeConverter<List<String>,String> {

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {

        return String.join(",",attribute);

    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return new ArrayList<>(Arrays.asList(dbData.split(" ")));
    }
}
