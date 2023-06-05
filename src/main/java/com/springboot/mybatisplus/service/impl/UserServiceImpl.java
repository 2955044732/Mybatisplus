package com.springboot.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.mybatisplus.mapper.UserMapper;
import com.springboot.mybatisplus.pojo.User;
import com.springboot.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @auther qwh
 * @create 2023-05-2023/5/17 18:45
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
