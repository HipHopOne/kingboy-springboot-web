package com.kingboy.controller;

import com.kingboy.common.utils.result.ApiResult;
import com.kingboy.dto.UserDTO;
import io.swagger.annotations.*;
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

    @ApiOperation(value = "获取用户信息", notes = "通过id获取用户")
    @GetMapping(value = "/{id}")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", paramType = "path", value = "用户ID"),
            @ApiImplicitParam(name = "username", paramType = "query", value = "用户名")
    })
    public ApiResult getUser(@PathVariable(value = "id") Integer id, @RequestParam String username) {
        return ApiResult.success(new UserDTO(id, username, LocalDateTime.now()));
    }

    @ApiOperation(value = "保存用户", notes = "保存用户，ID为后台自动生成")
    @PostMapping
    public ApiResult saveUser(@RequestBody UserDTO userDTO) {
        return ApiResult.success(userDTO);
    }

    @ApiOperation(value = "更新用户", notes = "更新用户，ID必传")
    @PutMapping
    public ApiResult updateUser(@RequestBody UserDTO userDTO) {
        return ApiResult.success("success");
    }

    @DeleteMapping("/{id}")
    public ApiResult removeUser(@ApiParam(value = "用户ID") @PathVariable(value = "id")  Integer id,
                                @ApiParam(value = "用户名") @RequestParam String username) {
        return ApiResult.success("success");
    }

}
