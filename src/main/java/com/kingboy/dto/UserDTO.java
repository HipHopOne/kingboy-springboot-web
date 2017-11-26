package com.kingboy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.kingboy.validate.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/26 下午1:41
 * @desc 用户载体.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    public interface UserSimpleView{}

    public interface UserDetailView extends UserSimpleView{}

    @JsonView(value = UserSimpleView.class)
    private Integer id;

    @JsonView(value = UserSimpleView.class)
    private String username;

    @NotBlank(message = "用户密码不能为空")
    @JsonView(value = UserDetailView.class)
    private String password;

    @JsonView(value = UserSimpleView.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Past(message = "出生日期错误")
    private LocalDateTime birth;
}
