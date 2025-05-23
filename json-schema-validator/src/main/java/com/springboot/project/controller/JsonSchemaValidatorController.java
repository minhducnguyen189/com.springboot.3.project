package com.springboot.project.controller;

import com.springboot.project.model.JsonSchemaValidator;
import com.springboot.project.model.JsonValidationResponse;
import com.springboot.project.service.JsonSchemaValidatorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JsonSchemaValidatorController {

    private final JsonSchemaValidatorService jsonSchemaValidatorService;

    @RequestMapping(method = RequestMethod.POST, path = "/v1/json/validator/schemas")
    public ResponseEntity<JsonSchemaValidator> createJsonSchemaValidator(
            @RequestBody String jsonSchema) {
        return new ResponseEntity<>(
                this.jsonSchemaValidatorService.createJsonSchemaValidator(jsonSchema),
                HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/v1/json/validator/schemas/{schemaName}")
    public ResponseEntity<List<JsonSchemaValidator>> getJsonSchemaValidatorByName(
            @PathVariable("schemaName") String schemaName) {
        return new ResponseEntity<>(
                this.jsonSchemaValidatorService.getJsonSchemaValidatorByName(schemaName),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/v1/json/validator/schemas/{schemaName}")
    public ResponseEntity<JsonValidationResponse> validateCustomerJson(
            @PathVariable("schemaName") String schemaName, @RequestBody String customerJson) {
        return new ResponseEntity<>(
                this.jsonSchemaValidatorService.validateJsonData(schemaName, customerJson),
                HttpStatus.OK);
    }
}
