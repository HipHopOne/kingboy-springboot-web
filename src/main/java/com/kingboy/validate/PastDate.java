package com.kingboy.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/12/29 下午7:24
 * @desc ${DESCRIPTION}.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastTimeValidate.class)
public @interface PastDate {

    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
