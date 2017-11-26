package com.kingboy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/26 下午3:34
 * @desc 用户查询.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryDTO {

    private String name;

    private Integer age;

    private Integer ageTo;

}
