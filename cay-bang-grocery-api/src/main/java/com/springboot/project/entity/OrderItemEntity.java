package com.springboot.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_item")
public class OrderItemEntity extends BaseEntity {

    @Column(name = "quantity")
    private Long quantity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity order;
}
