package com.springboot.project.entity.converter;

import jakarta.persistence.AttributeConverter;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class LocalTimeAttributeConverter implements AttributeConverter<Set<LocalTime>, String> {

    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(Set<LocalTime> times) {
        return CollectionUtils.isEmpty(times)
                ? StringUtils.EMPTY
                : times.stream().map(LocalTime::toString).collect(Collectors.joining(SEPARATOR));
    }

    @Override
    public Set<LocalTime> convertToEntityAttribute(String dbData) {
        if (StringUtils.isEmpty(dbData)) {
            return new HashSet<>();
        }
        String formatted = dbData.replaceAll("[//[//]]", dbData);
        return Arrays.stream(formatted.split(SEPARATOR))
                .map(String::trim)
                .map(LocalTime::parse)
                .collect(Collectors.toSet());
    }
}
