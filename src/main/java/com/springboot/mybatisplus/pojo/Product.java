package com.springboot.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther qwh
 * @create 2023-05-2023/5/18 15:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_product")
public class Product {
    private Long id;
    private String name;
    private Integer price;
    @Version
    private Integer version;
}
