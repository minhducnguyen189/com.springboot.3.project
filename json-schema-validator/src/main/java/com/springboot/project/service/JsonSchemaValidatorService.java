package com.springboot.project.service;

import com.springboot.project.model.JsonSchemaValidator;
import com.springboot.project.model.JsonValidationResponse;
import com.springboot.project.repository.JsonSchemaRepository;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JsonSchemaValidatorService {

    private static final ConcurrentHashMap<String, String> JSON_SCHEMA_STRUCTURE_CACHE =
            new ConcurrentHashMap<>();

    private final JsonSchemaRepository jsonSchemaRepository;
    private final CustomDateTimeValidator customDateTimeValidator;
    private final SequenceGeneratorService sequenceGeneratorService;

    public JsonSchemaValidator createJsonSchemaValidator(String json) {
        if (!this.validateInputJsonSchemaStructure(json))
            throw new IllegalArgumentException("Validation failed for input json schema!");
        JsonSchemaValidator jsonSchemaValidator = new JsonSchemaValidator();
        Document data = Document.parse(json);
        String schemaName = data.getString("name");
        Document value = data.get("value", Document.class);
        Optional<JsonSchemaValidator> latestJsonSchemaOpt =
                this.getlatestActiveJsonSchemaVersion(schemaName);
        if (latestJsonSchemaOpt.isEmpty()) {
            return this.saveNewJsonSchemaValidator(jsonSchemaValidator, schemaName, value);
        }
        JsonSchemaValidator latestJsonSchema = latestJsonSchemaOpt.get();
        latestJsonSchema.setStatus("inactive");
        this.jsonSchemaRepository.save(latestJsonSchema);
        this.saveNewJsonSchemaValidator(jsonSchemaValidator, schemaName, value);
        return this.jsonSchemaRepository.save(jsonSchemaValidator);
    }

    public List<JsonSchemaValidator> getJsonSchemaValidatorByName(String name) {
        return this.jsonSchemaRepository.findJsonSchemaValidatorByName(name);
    }

    public JsonValidationResponse validateJsonData(String schemaName, String jsonData) {
        Optional<JsonSchemaValidator> latestJsonSchemaOpt =
                this.getlatestActiveJsonSchemaVersion(schemaName);
        if (latestJsonSchemaOpt.isEmpty()) {
            return this.createJsonValidationResponse(false, jsonData, 0L);
        }
        JsonSchemaValidator latestJsonSchema = latestJsonSchemaOpt.get();
        Document jsonSchema = latestJsonSchema.getValue();
        boolean isValidJson = this.validateJson(jsonSchema.toJson(), jsonData);
        return this.createJsonValidationResponse(
                isValidJson, jsonData, latestJsonSchema.getVersion());
    }

    private JsonValidationResponse createJsonValidationResponse(
            boolean isValidJson, String jsonData, Long version) {
        JsonValidationResponse jsonValidationResponse = new JsonValidationResponse();
        jsonValidationResponse.setValidJson(isValidJson);
        jsonValidationResponse.setJsonInput(Document.parse(jsonData));
        jsonValidationResponse.setSchemaVersion(version);
        return jsonValidationResponse;
    }

    private JsonSchemaValidator saveNewJsonSchemaValidator(
            JsonSchemaValidator jsonSchemaValidator, String schemaName, Document data) {
        jsonSchemaValidator.setId(UUID.randomUUID());
        jsonSchemaValidator.setName(schemaName);
        jsonSchemaValidator.setStatus("active");
        jsonSchemaValidator.setVersion(
                sequenceGeneratorService.generateVersionSequence(schemaName));
        jsonSchemaValidator.setValue(data);
        return this.jsonSchemaRepository.save(jsonSchemaValidator);
    }

    private Optional<JsonSchemaValidator> getlatestActiveJsonSchemaVersion(String schemaName) {
        List<JsonSchemaValidator> jsonSchemaValidators =
                this.jsonSchemaRepository.findByOrderByVersionDesc(schemaName);
        for (JsonSchemaValidator jsonSchemaValidator : jsonSchemaValidators) {
            if (jsonSchemaValidator.getStatus().equalsIgnoreCase("active")) {
                return Optional.of(jsonSchemaValidator);
            }
        }
        return Optional.empty();
    }

    private boolean validateInputJsonSchemaStructure(String target) {
        String jsonSchemaStructure =
                JSON_SCHEMA_STRUCTURE_CACHE.computeIfAbsent(
                        "jsonSchemaStructureValidator",
                        (s) -> {
                            ClassPathResource resource =
                                    new ClassPathResource(
                                            "validation/JsonSchemaStructureValidator.json");
                            try {
                                return StreamUtils.copyToString(
                                        resource.getInputStream(), StandardCharsets.UTF_8);
                            } catch (IOException e) {
                                throw new RuntimeException(
                                        "There is no JsonSchemaStructureValidator in the source code!",
                                        e);
                            }
                        });
        return this.validateJson(jsonSchemaStructure, target);
    }

    private boolean validateJson(String validationSchema, String target) {
        try {
            SchemaLoader loader =
                    SchemaLoader.builder()
                            .addFormatValidator(customDateTimeValidator)
                            .schemaJson(new JSONObject(validationSchema))
                            .enableOverrideOfBuiltInFormatValidators()
                            .build();
            Schema schema = loader.load().build();
            schema.validate(new JSONObject(target));
            return true;
        } catch (ValidationException e) {
            return false;
        }
    }
}
