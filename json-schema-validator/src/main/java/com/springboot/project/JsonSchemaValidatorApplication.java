package com.springboot.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class JsonSchemaValidatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsonSchemaValidatorApplication.class, args);
    }
}
