package com.kingboy.user;

import com.kingboy.base.BaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/26 下午6:44
 * @desc 用户测试.
 */
public class UserTest extends BaseTest{


    /**
     * 测试获取批量用户，带分页
     * @throws Exception
     */
    @Test
    public void getUsersWhenSuccessTest() throws Exception {
        String contentAsString = mockMvc.perform(get("/user")
                .param("name", "king")
                .param("age", "20")
                .param("ageTo", "60")
                .param("page", "5")
                .param("size", "20")
                .param("sort", "age, desc")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    public void getUserWhenSuccessTest() throws Exception{
        String contentAsString = mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("小金"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    public void getUserWhenFailTest() throws Exception {
        mockMvc.perform(get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void saveUserWhenSuccessTest() throws Exception {
        String content = "{\"id\":3,\"username\":\"小金\",\"password\":\"king1\",\"birth\": \"2015-12-12 12:11\"}";
        String contentAsString = mockMvc.perform(post("/user")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    public void saveUserWhenFailNullPasswordTest() throws Exception {
        String content = "{\"id\":3,\"username\":\"小金\",\"password\":null,\"birth\": \"2015-12-12 12:11\"}";
        mockMvc.perform(post("/user")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void updateUserWhenSuccessTest() throws Exception {
        String content = "{\"id\":1,\"username\":\"小金\",\"password\":\"king1\",\"birth\": \"2016-12-12 12:11\"}";
        String contentAsString = mockMvc.perform(put("/user/1")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    public void updateUserWhenFailBirthFutureTest() throws Exception {
        String content = "{\"id\":1,\"username\":\"小金\",\"password\":\"king1\",\"birth\": \"2017-12-12 12:11\"}";
        mockMvc.perform(put("/user/1")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void deleteUserWhenSuccess() throws Exception {
        mockMvc.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
