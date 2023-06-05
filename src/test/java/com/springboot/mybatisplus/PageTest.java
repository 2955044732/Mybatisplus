package com.springboot.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.mybatisplus.mapper.UserMapper;
import com.springboot.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @auther qwh
 * @create 2023-05-2023/5/18 15:16
 */
@SpringBootTest
public class PageTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        //SELECT uid AS id,user_name AS name,age,email FROM t_user LIMIT ?
        Page<User> page = new Page<>(1,3);
        userMapper.selectPage(page,null);
        //获取分页数据
        List<User> pageRecords = page.getRecords();
        for (User user : pageRecords) {
            System.out.println(user);
        }
        System.out.println(page.getCurrent()+"当前页");//1
        System.out.println(page.getTotal()+"总记录数");//10
        System.out.println(page.getSize()+"每页显示的条数");//3
        System.out.println(page.getPages()+"总页面数");//4
        System.out.println(page.hasPrevious()+"是否有上一条数");//false
        System.out.println(page.hasNext()+"是否有下一条数目");//true

    }
}
