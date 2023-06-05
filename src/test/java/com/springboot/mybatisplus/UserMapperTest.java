package com.springboot.mybatisplus;

import com.springboot.mybatisplus.mapper.UserMapper;
import com.springboot.mybatisplus.pojo.SexEnum;
import com.springboot.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther qwh
 * @create 2023-05-2023/5/17 16:43
 */
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void SelectListTest(){
//  SELECT id,name,age,email FROM user
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }
    @Test
    public void insertTest(){
//  INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        User user = new User(null,"qwe",123,"123",null);
        int insert = userMapper.insert(user);
        System.out.println(insert);
        System.out.println(user.getId());
    }
    @Test
    public void deleteByIdTest(){
//  DELETE FROM user WHERE id=?
        int deleteById = userMapper.deleteById(1658758660097118210l);
        System.out.println(deleteById);
    }
    @Test
    public void deleteBatchIdsTest(){
//        通过id批量删除记录
//  DELETE FROM user WHERE id IN ( ? , ? )
        List<Long> list = Arrays.asList(1l, 2l);
        int deleteBatchIds = userMapper.deleteBatchIds(list);
        System.out.println(deleteBatchIds);
    }

    @Test
    public void deleteByMapTest(){
//     通过map条件删除记录
//   DELETE FROM user WHERE name = ? AND age = ?
        Map<String,Object> map = new HashMap<>();
        map.put("name","Tom");
        map.put("age",18);
        int deleteByMap = userMapper.deleteByMap(map);
        System.out.println(deleteByMap);
    }

    @Test
    public void updateByIdTest(){
        //修改
//  UPDATE user SET name=?, age=?, email=? WHERE id=?
        User user = new User(3L,"qwe",123,"qweasd123", SexEnum.MALE);
        int updateById = userMapper.updateById(user);
        System.out.println(updateById);
    }
    @Test
    public void selectByIdTest(){
        //查询
        //SELECT id,name,age,email FROM user WHERE id=?
        User user = userMapper.selectById(3L);
        System.out.println(user);
    }

    @Test
    public void selectBachIdsTest(){
        //根据多个id查询多个用户信息
        //SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
        List<Long> list = Arrays.asList(3L, 4L, 5L);
        List<User> userList = userMapper.selectBatchIds(list);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void selectByMapTest(){
        //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        Map<String,Object> map = new HashMap<>();
        map.put("name","qwe");
        map.put("age",123);
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }
    @Test
    public void selectListTest(){
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

//    public void selectMapById(){
//        Map<String, Object> map = userMapper.selectMapById(4L);
//        System.out.println(map);
//    }

}
