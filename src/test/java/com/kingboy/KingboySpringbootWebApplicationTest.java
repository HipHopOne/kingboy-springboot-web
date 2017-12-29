package com.kingboy;

import lombok.extern.apachecommons.CommonsLog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
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
@CommonsLog
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

    @Test
    public void getUserWhenSuccess() throws Exception {
        String result = mockMvc.perform(get("/user/小金")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("小金"))
                .andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void getUserWhenIdIsError() throws Exception {
        String result = mockMvc.perform(get("/user/aaaaaaaaaaaa")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    /**
     * 测试保存用户
     * @throws Exception
     */
    @Test
    public void saveUserWhenSuccessTest() throws Exception {
        String content = "{\"username\":\"asdfg\",\"password\":\"king1\",\"birth\": \"2015-12-12 12:11\"}";
        String contentAsString = mockMvc.perform(post("/user")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    public void saveUserWhenFailOfUsername() throws Exception {
        String content = "{\"username\":\"usernameisking\",\"password\":\"king1\",\"birth\": \"2015-12-12 12:11\"}";
        MockHttpServletResponse response = mockMvc.perform(post("/user")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse();
        log.info(response);
    }

}
