package com.comeback.app.common.validation;

import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidatorForEnum.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE, ElementType.METHOD})
public @interface EnumValidator {

  Class<? extends Enum<?>> value();

  String[] anyOf() default {};

  Class<?>[] groups() default {};
  
  Class<? extends Payload>[] payload() default {};
  
  String message() default "Must be one of {allowedValues}";
}
