package com.medinastr.security01.handler;

import com.medinastr.security01.EasyBankBackendApplication;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

public class DTOHandler {

  private static final Logger logger = LoggerFactory.getLogger(EasyBankBackendApplication.class);

  public static List<String> handle(Object dto) {
    Validator validator;
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      validator = factory.getValidator();
      Set<ConstraintViolation<Object>> violations = validator.validate(dto);
      if (violations.isEmpty()) {
        logger.info(dto.toString());
      } else {
        logger.error(dto.toString());
      }
      return violations.stream().map(ConstraintViolation::getMessage).toList();
    }
  }
}
