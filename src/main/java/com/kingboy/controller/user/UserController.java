package com.kingboy.controller.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.kingboy.service.user.dto.UserDTO;
import com.kingboy.service.user.dto.UserQueryDTO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/26 下午1:20
 * @desc 用户接口服务.
 * <p>
 * C:  url:/user     requestMethod:post     saveUser()/insertUser()
 * R:  url:/user     requestMethod:get      listUser()                加分页
 * url:/user/1   requestMethod:get      getUser()
 * U:  url:/user/1   requestMethod:put      updateUser()
 * D:  url:/user/1   requestMethod:delete   removeUser()/deleteUser
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {


    /**
     * 获取用户列表
     * @param userQueryDTO
     * @param pageable
     * @return
     */
    @GetMapping
    @JsonView(value = UserDTO.UserSimpleView.class)
    public List<UserDTO> listUser(UserQueryDTO userQueryDTO, @PageableDefault(page = 1, size = 10, sort = "username, asc") Pageable pageable) {

        System.out.println(ReflectionToStringBuilder.toString(userQueryDTO, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));
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
    public UserDTO saveUser(@RequestBody @Valid UserDTO userDTO) {
        System.out.println(ReflectionToStringBuilder.toString(userDTO, ToStringStyle.MULTI_LINE_STYLE));
        return userDTO;
    }

    /**
     * 更新用户信息
     * @param userDTO
     * @return
     */
    @PutMapping(value = "/{id:\\d+}")
    @JsonView(value = UserDTO.UserSimpleView.class)
    public UserDTO updateUser(@RequestBody @Valid UserDTO userDTO) {
        System.out.println(ReflectionToStringBuilder.toString(userDTO, ToStringStyle.MULTI_LINE_STYLE));
        return userDTO;
    }


    /**
     * 删除用户
     * @param id
     */
    @DeleteMapping(value = "/{id:\\d+}")
    public void removeUser(@PathVariable Integer id) {
        System.out.println(id);
    }


}
