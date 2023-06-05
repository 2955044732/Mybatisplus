package com.springboot.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther qwh
 * @create 2023-05-2023/5/17 16:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")//将不同实体类表明设置
public class User {
    //@TableId将属性指定的字段指定为主键
    //value="数据库里的主键字段"
    //type = IdType.AUTO --》id生成策略 auto自增 IdType.NONE雪花算法
//    type也可以通过全局配置在yml
    @TableId(value = "uid")
    private Long id;
    @TableField("user_name")
    //将数据库中字段与实体类不匹配的属性
    // (必须设置的和数据库中表的字段一模一样)
    // 设置为匹配
    private String name;
    private Integer age;
    private String email;
    private SexEnum sex;
}
