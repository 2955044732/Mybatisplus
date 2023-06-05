package com.springboot.mybatisplus;

import com.springboot.mybatisplus.mapper.UserMapper;
import com.springboot.mybatisplus.pojo.SexEnum;
import com.springboot.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @auther qwh
 * @create 2023-05-2023/5/18 16:44
 */
@SpringBootTest
public class EnumTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test(){
        User user = new User();
        user.setName("qwe");
        user.setAge(123);
        user.setSex(SexEnum.MALE);
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }
}
