package com.kingboy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/26 下午10:44
 * @desc rest服务测试.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KingboySpringbootWebApplicationTest {

    @Resource
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    /**
     * 测试获取单个用户
     * @throws Exception
     */
    @Test
    public void getUserWhenSuccessTest() throws Exception{
        String contentAsString = mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("小金"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }

    /**
     * 测试错误的ID来获取用户
     * @throws Exception
     */
    @Test
    public void getUserWhenFailTest() throws Exception {
        mockMvc.perform(get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }


    /**
     * 测试保存用户
     * @throws Exception
     */
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

    /**
     * 测试密码为空时，保存用户
     * @throws Exception
     */
    @Test
    public void saveUserWhenFailNullPasswordTest() throws Exception {
        String content = "{\"id\":3,\"username\":\"小金\",\"password\":null,\"birth\": \"2015-12-12 12:11\"}";
        mockMvc.perform(post("/user")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }

}
