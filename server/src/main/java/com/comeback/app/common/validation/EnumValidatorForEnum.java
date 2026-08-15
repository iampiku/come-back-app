package com.comeback.app.common.validation;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidatorForEnum implements ConstraintValidator<EnumValidator, Enum<?>> {

  private Set<String> allowed;
  private String allowedDisplay;

  @Override
  public void initialize(EnumValidator annotation) {
    Set<String> constants = Arrays.stream(annotation.value().getEnumConstants())
      .map(Enum::name)
      .collect(Collectors.toCollection(LinkedHashSet::new));

    if (annotation.anyOf().length == 0) {
      this.allowed = constants;
    } else {
      
    }
  }

  @Override
  public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
    throw new UnsupportedOperationException("Unimplemented method 'isValid'");
  }
}