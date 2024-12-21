package com.github.francodurand.nomadnation.shared.domain.model.aggregates;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;

import lombok.Getter;

/**
 * Base class for all aggregate roots that require auditing.
 *
 * @param <T> the type of the aggregate root
 * @summary The class is an abstract class that extends the
 *          {@link AbstractAggregateRoot} class and adds auditing fields to the
 *          class.
 */
@Getter
public class AuditableAbstractAggregateRoot<T extends AbstractAggregateRoot<T>> extends AbstractAggregateRoot<T> {
    @Id
    private String id;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    /**
     * Registers a domain event.
     *
     * @param event the domain event to register
     */
    public void addDomainEvent(Object event) {
        super.registerEvent(event);
    }
}
