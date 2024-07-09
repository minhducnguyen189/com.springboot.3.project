package com.springboot.project.entity.converter;

import jakarta.persistence.AttributeConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LocalTimeAttributeConverter implements AttributeConverter<List<LocalTime>, String> {

    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(List<LocalTime> times) {
        return CollectionUtils.isEmpty(times)
                ? StringUtils.EMPTY
                : times.stream()
                .map(LocalTime::toString)
                .collect(Collectors.joining(SEPARATOR));
    }

    @Override
    public List<LocalTime> convertToEntityAttribute(String dbData) {
        if (StringUtils.isEmpty(dbData)) {
            return new ArrayList<>();
        }
        String formatted = dbData.replaceAll("[//[//]]", dbData);
        return Arrays.stream(formatted.split(SEPARATOR))
                .map(String::trim)
                .map(LocalTime::parse)
                .collect(Collectors.toList());
    }
}
