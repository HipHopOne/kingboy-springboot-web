package com.kingboy.controller.user;

import com.kingboy.common.utils.page.PageParam;
import com.kingboy.service.user.dto.UserDTO;
import com.kingboy.service.user.dto.UserQueryDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
     * @param userQueryDTO
     * @param pageParam
     * @return
     */
    @GetMapping
    public List<UserDTO> listUser(UserQueryDTO userQueryDTO, PageParam pageParam) {
        return Stream.of(new UserDTO(1, "小金", "king1", LocalDateTime.now()),
                new UserDTO(2, "小明", "king2", LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @GetMapping(value = "/{id:\\d+}")
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
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        return userDTO;
    }

    /**
     * 更新用户信息
     * @param userDTO
     * @return
     */
    @PutMapping(value = "/{id:\\d+}")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return userDTO;
    }


    /**
     * 删除用户
     * @param id
     */
    @DeleteMapping(value = "/{id:\\d+}")
    public void removeUser(@PathVariable Integer id) {
        //删除用户
    }


}
