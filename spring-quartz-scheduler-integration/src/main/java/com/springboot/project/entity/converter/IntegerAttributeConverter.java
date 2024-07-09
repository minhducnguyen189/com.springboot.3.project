package com.springboot.project.entity.converter;


import jakarta.persistence.AttributeConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class IntegerAttributeConverter implements AttributeConverter<List<Integer>, String> {

    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(List<Integer> integers) {
        return CollectionUtils.isEmpty(integers)
                ? StringUtils.EMPTY
                : integers.stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining(SEPARATOR));
    }

    @Override
    public List<Integer> convertToEntityAttribute(String dbData) {
        if (StringUtils.isEmpty(dbData)) {
            return new ArrayList<>();
        }
        String formatted = dbData.replaceAll("[//[//]]", dbData);
        return Arrays.stream(formatted.split(SEPARATOR))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
