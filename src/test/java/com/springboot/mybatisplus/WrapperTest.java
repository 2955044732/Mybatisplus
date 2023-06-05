package com.springboot.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.springboot.mybatisplus.mapper.UserMapper;
import com.springboot.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @auther qwh
 * @create 2023-05-2023/5/17 21:30
 */
@SpringBootTest
public class WrapperTest{
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){
        //条件查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name","a").between("age",20,30).isNotNull("email");
        //SELECT uid AS id,user_name AS name,age,email FROM t_user WHERE (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user : list) {
            System.out.println(user);//User(id=2, name=Sandy, age=21, email=test4@baomidou.com)
        }
    }
    @Test
    public void test02(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //首先按照年龄降序,再按照id升序
        queryWrapper.orderByDesc("age").orderByAsc("uid");
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user : list) {
            System.out.println(user);
        }

    }
    @Test
    public void test03(){
        //修改的条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",20).like("user_name","a").or().isNull("email");
        User user = new User();
        user.setEmail("123.com");
        user.setName("16666");
        //UPDATE t_user SET user_name=?, email=? WHERE (age > ? AND user_name LIKE ? OR email IS NULL)
        int update = userMapper.update(user, queryWrapper);
        System.out.println(update);
    }
    @Test
    public void test04(){
        //条件
        //UPDATE t_user SET user_name=?, email=? WHERE (user_name LIKE ? AND (age > ? OR email IS NULL))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name","a")
                .and(i->i.gt("age",20).or().isNull("email"));
        User user = new User();
        user.setEmail("123.com");
        user.setName("16666");
        //UPDATE t_user SET user_name=?, email=? WHERE (user_name LIKE ? AND (age > ? OR email IS NULL))
        int update = userMapper.update(user, queryWrapper);
        System.out.println(update);
    }

    @Test
    public void test05(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_name","age");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }
    @Test
    public void test06(){
        //QueryWrapper实现子查询
        //SELECT uid AS id,user_name AS name,age,email
        // FROM t_user WHERE (
        //                  uid IN (
        //                      select uid from t_user where uid <= 100
        //                      )
        //                   )
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("uid","select uid from t_user where uid <= 100");
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user : list) {
            System.out.println(user);
        }

    }
    @Test
    public void test07(){
        //将用户名包   含a并且（年龄大于20或邮箱为null）的用户信息修改
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.like("user_name","a").and(i->i.gt("age",20).or().isNull("email"));
        userUpdateWrapper.set("user_name","6666666").set("age",666666);
        //UPDATE t_user SET user_name=?,age=? WHERE (user_name LIKE ? AND (age > ? OR email IS NULL))
        int update = userMapper.update(null, userUpdateWrapper);
        System.out.println(update);
    }
    @Test
    public void test08(){
        String username = " ";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            queryWrapper.like("user_name",username);
        }
        if (ageBegin != null){
            queryWrapper.gt("age",ageBegin);
        }
        if (ageEnd != null){
            queryWrapper.lt("age",ageEnd);
        }
        //SELECT uid AS id,user_name AS name,age,email FROM t_user WHERE (age < ?)
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user : list) {
            System.out.println(user);
        }
    }
    @Test
    public void test09(){
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),"user_name",username)
                .gt(ageBegin!=null,"age",ageBegin)
                .le(ageEnd!=null,"age",ageEnd);
        //SELECT uid AS id,user_name AS name,age,email FROM t_user WHERE (user_name LIKE ? AND age <= ?)
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }
    @Test
    public void test10(){

    }
}
