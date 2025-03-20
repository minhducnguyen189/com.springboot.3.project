package com.springboot.project.frontend.integration.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class SampleController {

  @RequestMapping(
      method = RequestMethod.GET,
      path = "/v1/messages",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<String>> getMessages() {
    return new ResponseEntity<>(Arrays.asList("Message 1", "Message 2"), HttpStatus.OK);
  }
}
