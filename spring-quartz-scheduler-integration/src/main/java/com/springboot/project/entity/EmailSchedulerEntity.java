package com.springboot.project.entity;

import com.springboot.project.entity.converter.IntegerAttributeConverter;
import com.springboot.project.entity.converter.LocalTimeAttributeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Table(name = "email_scheduler")
@Entity
@Getter
@Setter
public class EmailSchedulerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "email_content")
    private String emailContent;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "days_of_week")
    @Convert(converter = IntegerAttributeConverter.class)
    private Set<Integer> daysOfWeek;

    @Column(name = "times_of_day")
    @Convert(converter = LocalTimeAttributeConverter.class)
    private Set<LocalTime> timesOfDay;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    private void prePersist() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = Instant.now();
    }

}
