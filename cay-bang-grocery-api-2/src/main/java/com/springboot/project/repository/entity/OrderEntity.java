package com.springboot.project.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Generated;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

  @Generated
  @Column(name = "order_number")
  private Long orderNumber;

  @Column(name = "customer_name")
  private String customerName;

  @Column(name = "address")
  private String address;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "email")
  private String email;

  @Fetch(FetchMode.SUBSELECT)
  @OneToMany(
      mappedBy = "order",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private List<OrderItemEntity> items;
}
