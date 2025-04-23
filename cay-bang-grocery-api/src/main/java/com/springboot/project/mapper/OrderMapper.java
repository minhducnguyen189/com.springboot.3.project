package com.springboot.project.mapper;

import com.springboot.project.entity.OrderEntity;
import com.springboot.project.entity.OrderItemEntity;
import com.springboot.project.entity.ProductEntity;
import com.springboot.project.generated.model.OrderDetail;
import com.springboot.project.generated.model.OrderItemDetail;
import com.springboot.project.generated.model.OrderRequest;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    OrderEntity toOrderEntity(OrderRequest orderRequest);

    List<OrderDetail> toOrderDetails(List<OrderEntity> orderEntities);

    @Mapping(target = "totalPrice", source = "items")
    @Mapping(target = "orderItems", source = "items")
    OrderDetail toOrderDetail(OrderEntity orderEntity);

    @Mapping(target = "totalPrice", source = "items")
    default Long toTotalPrice(List<OrderItemEntity> orderItemEntities) {
        return orderItemEntities.stream()
                .mapToLong(order -> order.getQuantity() * order.getProduct().getPrice())
                .sum();
    }

    default List<OrderItemDetail> toOrderItemDetails(List<OrderItemEntity> orderItemEntities) {
        List<OrderItemDetail> orderItemDetails = new ArrayList<>();
        for (OrderItemEntity orderItem : orderItemEntities) {
            OrderItemDetail orderItemDetail = new OrderItemDetail();
            ProductEntity product = orderItem.getProduct();
            orderItemDetail.setQuantity(orderItem.getQuantity());
            orderItemDetail.setTotalPrice(
                    orderItem.getQuantity() * orderItem.getProduct().getPrice());
            orderItemDetail.setCategories(product.getCategories());
            orderItemDetail.setName(product.getName());
            orderItemDetail.setDescription(product.getDescription());
            orderItemDetail.setRating(product.getRating());
            orderItemDetail.setProductNumber(product.getProductNumber());
            orderItemDetail.setPricePerItem(product.getPrice());
            orderItemDetail.setSummary(product.getSummary());
            orderItemDetails.add(orderItemDetail);
        }
        return orderItemDetails;
    }
}
