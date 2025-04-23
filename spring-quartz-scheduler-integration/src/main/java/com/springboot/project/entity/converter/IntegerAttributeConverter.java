package com.springboot.project.entity.converter;

import jakarta.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class IntegerAttributeConverter implements AttributeConverter<Set<Integer>, String> {

    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(Set<Integer> integers) {
        return CollectionUtils.isEmpty(integers)
                ? StringUtils.EMPTY
                : integers.stream().map(Objects::toString).collect(Collectors.joining(SEPARATOR));
    }

    @Override
    public Set<Integer> convertToEntityAttribute(String dbData) {
        if (StringUtils.isEmpty(dbData)) {
            return new HashSet<>();
        }
        String formatted = dbData.replaceAll("[//[//]]", dbData);
        return Arrays.stream(formatted.split(SEPARATOR))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }
}
