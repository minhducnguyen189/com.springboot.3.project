package com.springboot.project.controller;

import com.springboot.project.model.Customer;
import com.springboot.project.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private final CustomerService customerService;

    @RequestMapping(method = RequestMethod.POST, path = "/v1/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody String customerJson) {
        return new ResponseEntity<>(this.customerService.createCustomer(customerJson), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/v1/customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(this.customerService.getCustomers(), HttpStatus.OK);
    }

}
