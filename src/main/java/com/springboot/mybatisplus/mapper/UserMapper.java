package com.springboot.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.mybatisplus.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @auther qwh
 * @create 2023-05-2023/5/17 16:37
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
//    添加一些自定义的sql
    //方法名对应mapper/UserMapper.xml里的select id = "方法名"
//    Map<String,Object> selectMapById(Long id);

}
