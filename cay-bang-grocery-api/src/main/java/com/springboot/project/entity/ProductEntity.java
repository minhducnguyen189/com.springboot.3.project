package com.springboot.project.entity;

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
@Table(name = "products")
public class ProductEntity extends BaseEntity {

  @Generated
  @Column(name = "product_number")
  private Long productNumber;

  @Column(name = "name")
  private String name;

  @Column(name = "summary")
  private String summary;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private Long price;

  @Column(name = "rating")
  private Integer rating;

  @Column(name = "categories")
  @Convert(converter = ListStringAttributeConverter.class)
  private List<String> categories;

  @Fetch(FetchMode.SUBSELECT)
  @OneToMany(
      mappedBy = "product",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private List<FileStorageEntity> files;
}
