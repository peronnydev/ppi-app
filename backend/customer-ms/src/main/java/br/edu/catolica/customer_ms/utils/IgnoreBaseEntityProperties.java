package br.edu.catolica.customer_ms.utils;

import org.mapstruct.Mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// createdAt, updatedAt e isActive não são expostos pelo @Builder das entidades
// (ficam no BaseEntity) e são preenchidos pelo @PrePersist
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
@Mapping(target = "id", ignore = true)
public @interface IgnoreBaseEntityProperties {
}
