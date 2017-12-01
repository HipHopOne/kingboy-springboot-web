package com.kingboy.common.config.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/26 下午7:44
 * @desc ${DESCRIPTION}.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastTimeValidate.class)
public @interface Past {

    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
