package com.kingboy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/26 下午1:41
 * @desc 用户载体.
 */
@Data
@ApiModel(value = "用户信息")
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @ApiModelProperty(value = "用户ID", example = "1")
    private Integer id;

    @ApiModelProperty(value = "用户名", example = "king")
    private String username;

    @ApiModelProperty(value = "生日", example = "1999-12-31 12:59")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime birthday;
}
