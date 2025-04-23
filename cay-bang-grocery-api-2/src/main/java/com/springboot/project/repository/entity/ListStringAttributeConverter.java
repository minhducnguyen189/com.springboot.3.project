package com.springboot.project.repository.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

@Converter
public class ListStringAttributeConverter implements AttributeConverter<List<String>, String> {

    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(List<String> items) {
        if (CollectionUtils.isEmpty(items)) {
            return null;
        }
        return StringUtils.join(items, SEPARATOR);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (StringUtils.isEmpty(dbData)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(List.of(dbData.split(SEPARATOR)));
    }
}
