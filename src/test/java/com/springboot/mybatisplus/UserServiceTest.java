package com.springboot.mybatisplus;

import com.springboot.mybatisplus.pojo.User;
import com.springboot.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther qwh
 * @create 2023-05-2023/5/17 19:12
 */
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void getCountTest(){
        //查询总行数
        long count = userService.count();
        System.out.println(count);
    }
    @Test
    public void saveBatchTest(){
        //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            User user = new User();
//            user.setName("asd"+i);
            user.setAge(20+i);
            user.setEmail("qwe"+i+"qq.com");
            list.add(user);
        }
        boolean saveBatch = userService.saveBatch(list);
        System.out.println(saveBatch);
    }
    @Test
    public void test(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","qwe");
        map.put("age",123);
        List<User> list = userService.listByMap(map);
        System.out.println(list);

    }

}
