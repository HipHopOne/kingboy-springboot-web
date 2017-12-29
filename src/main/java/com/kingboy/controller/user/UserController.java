package com.kingboy.controller.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.kingboy.service.user.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/26 下午1:20
 * @desc 用户接口服务.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {


    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    @JsonView(value = UserDTO.UserDetailView.class)
    public UserDTO getUser(@PathVariable String id) {
        UserDTO userDTO = new UserDTO(1, "小金", "king1", LocalDateTime.now());
        return userDTO;
    }

    /**
     * 保存用户
     * @param userDTO
     * @return
     */
    @PostMapping
    @JsonView(value = UserDTO.UserSimpleView.class)
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        return userDTO;
    }


}
