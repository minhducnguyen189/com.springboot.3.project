package com.springboot.project.service;

import com.springboot.project.generated.model.OrderDetail;
import com.springboot.project.generated.model.OrderFilterResult;
import com.springboot.project.generated.model.OrderRequest;

public interface IOrderService {

    OrderDetail createOrder(OrderRequest orderRequest);

    OrderFilterResult getOrders(Integer pageNumber, Integer pageSize);
}
