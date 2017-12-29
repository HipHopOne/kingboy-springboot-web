package com.kingboy.controller.user;

import com.kingboy.common.utils.page.PageParam;
import com.kingboy.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/26 下午1:20
 * @desc 用户接口服务.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {


    /**
     * 获取用户列表
     * get user list
     * @param pageParam
     * @return
     */
    @GetMapping
    public List<UserDTO> listUser(PageParam pageParam) {
        return Stream.of(new UserDTO(1, "小金", "king1", LocalDateTime.now()),
                new UserDTO(2, "小明", "king2", LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    /**
     * 根据ID获取用户信息
     * get user info by id
     * @param id
     * @return
     */
    @GetMapping(value = "/{id:\\d+}")
    public UserDTO getUser(@PathVariable String id) {
        UserDTO userDTO = new UserDTO(1, "小金", "king1", LocalDateTime.now());
        return userDTO;
    }

    /**
     * 获取用户名为{username}，并且年龄小于{age}的用户
     * get user by username and age which less than {age}
     * @return
     */
    @GetMapping(value = "/name/{username}/age/less/{age}")
    public List<UserDTO> getUserByUsernameAndAge(@PathVariable String username, @PathVariable Integer age) {
        UserDTO userDTO = new UserDTO(1, "小金", "king1", LocalDateTime.now());
        return Arrays.asList(userDTO);
    }

    /**
     * 保存用户
     * save user
     * @param userDTO
     * @return
     */
    @PostMapping
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        return userDTO;
    }

    /**
     * 更新用户信息
     * update user info
     * @param userDTO
     * @return
     */
    @PutMapping(value = "/{id:\\d+}")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return userDTO;
    }


    /**
     * 删除用户
     * delete user
     * @param id
     */
    @DeleteMapping(value = "/{id:\\d+}")
    public void removeUser(@PathVariable Integer id) {
        //删除用户
    }


}
