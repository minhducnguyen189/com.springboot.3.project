package com.springboot.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order")
public class OrderEntity extends BaseEntity {

    private String customerName;
    private String address;
    private String phoneNumber;
    private String email;

}
