package com.kingboy.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/12/29 下午9:27
 * @desc 用户数据载体.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    //不显示密码
    public interface CommonView {}

    //显示密码
    public interface AdminView extends CommonView {}

    @JsonView(value = CommonView.class)
    private String username;

    @JsonView(value = AdminView.class )
    private String address;
}
