package com.springboot.project.service;

import com.springboot.project.entity.OrderEntity;
import com.springboot.project.entity.OrderItemEntity;
import com.springboot.project.entity.ProductEntity;
import com.springboot.project.generated.model.OrderDetail;
import com.springboot.project.generated.model.OrderItemRequest;
import com.springboot.project.generated.model.OrderRequest;
import com.springboot.project.mapper.OrderMapper;
import com.springboot.project.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public OrderDetail createOrder(OrderRequest orderRequest) {
        OrderEntity orderEntity = OrderMapper.MAPPER.toOrderEntity(orderRequest);
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        for (OrderItemRequest orderItemRequest: orderRequest.getOrderItems()) {
            ProductEntity productEntity = this.productService.getProductByProductNumber(orderItemRequest.getProductNumber());
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setQuantity(orderItemRequest.getQuantity());
            orderItemEntity.setProduct(productEntity);
            orderItemEntity.setOrder(orderEntity);
            orderItemEntities.add(orderItemEntity);
        }
        orderEntity.setItems(orderItemEntities);
        orderEntity = this.orderRepository.save(orderEntity);

        return OrderMapper.MAPPER.toOrderDetail(orderEntity);
    }

}
