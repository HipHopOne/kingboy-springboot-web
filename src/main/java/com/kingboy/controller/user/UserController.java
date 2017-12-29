package com.kingboy.controller.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.kingboy.common.utils.page.PageParam;
import com.kingboy.service.user.dto.UserDTO;
import com.kingboy.service.user.dto.UserQueryDTO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {


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


}
