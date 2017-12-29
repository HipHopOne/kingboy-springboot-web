package com.kingboy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/12/29 下午10:09
 * @desc 分组校验实体.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupValidDTO {

    public interface SaveGroup extends Default {}

    public interface UpdateGroup extends Default {}

    @Null(groups = {SaveGroup.class}, message = "不需要传入用户ID")
    @NotNull(groups = {UpdateGroup.class}, message = "用户ID不能为空")
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String username;

}
