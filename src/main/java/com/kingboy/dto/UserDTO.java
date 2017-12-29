package com.kingboy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kingboy.validate.PastDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/26 下午1:41
 * @desc 用户载体.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    //该参数必须为空
    @Null(message = "无需ID")
    private Integer id;

    //根据正则校验手机号是否是由数字组成
    @Pattern(regexp = "^\\d{11}$", message = "手机格式不正确,不是11位")
    private String telephone;

    //校验该对象是否为null
    //对于String来说，空字符串可通过校验，所以String应该使用@NotBlank进行校验，此处仅做示例而已。
    @NotNull(message = "联系人不能为空")
    private String friendName;

    //校验对象是否是空对象，可用于Array,Collection,Map,String
    @NotEmpty(message = "家庭成员不能为空")
    private List families;

    //校验长度，可以用于Array,Collection,Map,String
    @Size(min = 4, max = 8, message = "用户名长度错误")
    //校验长度，只能用于String
    @Length(min = 4, max = 8, message = "用户名长度错误")
    private String username;

    //javax校验
    @Max(value = 200, message = "年龄一般不会超过200")
    @Min(value = 1, message = "尚未出生")
    //hibernate校验，效果等同
    @Range(min = 0, max = 200)
    private Integer age;

    //校验参数是否是False, 相反的是@AssertTrue
    @AssertFalse(message = "用户初始化无需冻结")
    private Boolean lock;

    //String专用
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 12, message = "密码长度不对")
    private String password;

    //使用自定义校验注解->校验时间
    @PastDate(message = "生日只能为以前的时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime birth;

    //校验Email
    @Email(message = "邮件地址不正确")
    private String email;
}
