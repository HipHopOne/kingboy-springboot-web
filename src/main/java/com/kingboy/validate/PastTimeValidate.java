package com.kingboy.validate;

import lombok.extern.apachecommons.CommonsLog;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/12/29 下午7:28
 * @desc .
 */
@CommonsLog
public class PastTimeValidate implements ConstraintValidator<PastDate, LocalDateTime> {

   @Override
   public void initialize(PastDate constraintAnnotation) {
      log.info("init enum PastDate");
   }

   @Override
   public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext context) {
      return localDateTime.isBefore(LocalDateTime.now()) ? true : false;
   }
}
