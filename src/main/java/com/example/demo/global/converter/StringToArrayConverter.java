package com.example.demo.global.converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.AttributeConverter;

public class StringToArrayConverter implements AttributeConverter<List<Integer>,String> {
    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<Integer> attribute) {
        return attribute.stream().map(String::valueOf).collect(Collectors.joining(SPLIT_CHAR));
    }

    @Override
    public List<Integer> convertToEntityAttribute(String dbData) {
        if(dbData == ""){
            return Arrays.asList();
        }
        return Arrays.stream(dbData.split(SPLIT_CHAR))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
