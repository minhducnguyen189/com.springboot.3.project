package com.springboot.project.controller;

import com.springboot.project.generated.api.OrderApi;
import com.springboot.project.generated.model.OrderDetail;
import com.springboot.project.generated.model.OrderFilterResult;
import com.springboot.project.generated.model.OrderRequest;
import com.springboot.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class OrderController implements OrderApi {

  private final OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public ResponseEntity<OrderDetail> createOrder(OrderRequest orderRequest) {
    return new ResponseEntity<>(this.orderService.createOrder(orderRequest), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<OrderFilterResult> getOrders(
      Optional<Integer> pageNumber, Optional<Integer> pageSize) {
    OrderFilterResult orderFilterResult =
        this.orderService.getOrders(pageNumber.orElse(0), pageSize.orElse(10));
    return new ResponseEntity<>(orderFilterResult, HttpStatus.OK);
  }
}
