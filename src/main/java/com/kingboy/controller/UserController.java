package com.kingboy.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kingboy.dto.UserDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/12/29 下午9:28
 * @desc 用户控制层.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    /**
     * 返回普通视图，不带地址
     * @return
     */
    @GetMapping(value = "/common")
    @JsonView(value = UserDTO.CommonView.class)
    public UserDTO getCommonView() {
        return new UserDTO("kingboy", "北京");
    }

    /**
     * 返回管理员视图，显示地址
     * @return
     */
    @GetMapping(value = "/admin")
    @JsonView(value = UserDTO.AdminView.class)
    public UserDTO getAdminView() {
        return new UserDTO("kingboy", "北京");
    }

}
