package com.kingboy.common.config.validate;

import com.kingboy.controller.user.UserController;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/26 下午7:48
 * @desc 校验时间，是否是过去.
 */
public class PastTimeValidate implements ConstraintValidator<Past, LocalDateTime>{

    //可以注入容器内的bean，进行操作
    @Resource
    UserController userController;

    @Override
    public void initialize(Past constraintAnnotation) {
        System.out.println("初始化时间校验注解");
    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {

        if (value.isAfter(LocalDateTime.now())) {
            System.out.println(context.getDefaultConstraintMessageTemplate());
            return false;
        }

        return true;
    }
}
