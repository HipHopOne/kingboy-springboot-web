package com.kingboy.controller;

import com.kingboy.dto.UserDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/26 下午1:20
 * @desc 用户接口服务.
 */
@Validated
@RestController
@RequestMapping(value = "/user")
public class UserController {

    /**
     * 校验请求参数
     */
    @GetMapping
    public UserDTO getUser(@Size(min = 5, max = 8, message = "用户名长度超出限制") String username) {
        ArrayList<String> families = new ArrayList<>();
        families.add("father");
        families.add("mother");
        families.add("sister");
        return new UserDTO(1, "13133336608", "friend",
                families, "kingboy", 24, false, "123456",
                LocalDateTime.now(), "kingboyworld@163.com");
    }

    /**
     * 校验请求对象, 正确json请求体如下
     *
     *  {
     *      "telephone": "18652002252",
     *      "friendName": "friend",
     *      "families": [
     *              "father",
     *              "mother",
     *              "sister"
     *      ],
     *      "username": "kingboy",
     *      "age": 24,
     *      "lock": false,
     *      "password": "123456",
     *      "birth": "2017-12-29 19:45",
     *      "email": "kingboyworld@163.com"
     *  }
     */
    @PostMapping
    public UserDTO saveUserOuter(@RequestBody @Valid UserDTO userDTO) {
        return userDTO;
    }

}
