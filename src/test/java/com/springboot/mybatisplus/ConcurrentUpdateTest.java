package com.springboot.mybatisplus;

import com.springboot.mybatisplus.mapper.ProductMapper;
import com.springboot.mybatisplus.pojo.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @auther qwh
 * @create 2023-05-2023/5/18 15:51
 */
@SpringBootTest
public class ConcurrentUpdateTest {
    @Autowired
    private ProductMapper productMapper;
    @Test
    public void test01(){
        //小李
        Product productli = productMapper.selectById(1L);
        System.out.println("小李的价格"+productli.getPrice());//小李的价格100
        //小王
        Product productwan = productMapper.selectById(1L);
        System.out.println("小王取出的价格"+productwan.getPrice());//小王取出的价格100
        //小李将价格提高50，存入数据库
        productli.setPrice(productli.getPrice()+50);
        int updateli = productMapper.updateById(productli);
        System.out.println("小李修改的条数"+updateli);//小李修改的条数1
        //小王将商品减30 存入数据库
        productwan.setPrice(productwan.getPrice()-30);
        int updatewan = productMapper.updateById(productwan);
        System.out.println("小王修改的条数"+updatewan);//小王修改的条数1
        //最后的结果
        Product product = productMapper.selectById(1L);
        System.out.println("最后的结果"+product.getPrice());//最后的结果
    }

    @Test
    public void test02(){
        //乐观锁
        Product productli = productMapper.selectById(1L);

        Product productwan = productMapper.selectById(1L);

        productli.setPrice(productli.getPrice()+50);
        int liresult = productMapper.updateById(productli);

        System.out.println("小李修改的结果"+liresult);//小李修改的结果1

        productwan.setPrice(productwan.getPrice()-30);
        int wanresult = productMapper.updateById(productwan);
        System.out.println("小王修改的结果"+wanresult);//版本号发生了改变 sql 不执行 小王修改的结果0

        if (wanresult==0){
            Product newproductwan = productMapper.selectById(1L);
            newproductwan.setPrice(newproductwan.getPrice()-20);
            int update = productMapper.updateById(newproductwan);
            System.out.println(update);
        }
        //老板
        Product product = productMapper.selectById(1L);
        System.out.println("老板查询的价格"+product);
    }
}
