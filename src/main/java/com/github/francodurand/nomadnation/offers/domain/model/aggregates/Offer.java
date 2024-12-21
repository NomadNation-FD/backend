package com.github.francodurand.nomadnation.offers.domain.model.aggregates;

import org.springframework.data.mongodb.core.mapping.Document;

import com.github.francodurand.nomadnation.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import lombok.Getter;

@Document("offers")
@Getter
public class Offer extends AuditableAbstractAggregateRoot<Offer> {
    private String destination;
    private String description;
    private Float price;
    private String[] images;

    public Offer() {
    }
}
