package com.springboot.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "created_at")
  private OffsetDateTime createdAt;

  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

  @PrePersist
  private void prePersist() {
    OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
    this.createdAt = now;
    this.updatedAt = now;
  }

  @PreUpdate
  private void preUpdate() {
    this.updatedAt = OffsetDateTime.now(ZoneOffset.UTC);
  }
}
