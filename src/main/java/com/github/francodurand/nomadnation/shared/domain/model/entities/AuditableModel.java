package com.github.francodurand.nomadnation.shared.domain.model.entities;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;

@Getter
public class AuditableModel {
    @Id
    private String id;

    @CreatedDate
    protected Date createdAt;

    @LastModifiedDate
    protected Date updatedAt;
}