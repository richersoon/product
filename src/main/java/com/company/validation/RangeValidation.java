package com.company.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc

/**
 * The Interface EnumerationValidation.
 */
@Documented
@Constraint(validatedBy = RangeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER,
        ElementType.CONSTRUCTOR })
@Retention(RetentionPolicy.RUNTIME)
public @interface RangeValidation {

    /**
     * Message.
     *
     */
    String message();
    /**
     * Groups.
     *
     */
    Class<?>[] groups() default {};

    /**
     * Payload.
     *
     */
    Class<? extends Payload>[] payload() default {};

    int minInclusive();

    int maxInclusive();

    boolean isOptional() default false;
}