package com.example.wechat;

import com.example.wechat.dao.ProductCategory;
import com.example.wechat.repository.ProductCategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    ProductCategoryRepository categoryRepository;

    @Test
    public void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void findOneTest(){
        Optional<ProductCategory> productCategory = categoryRepository.findById(1);
        if (productCategory.isPresent()) {
            System.out.println(productCategory);
        }
    }

    @Test
    public void save(){
        ProductCategory category = new ProductCategory();
        category.setCategoryName("糖果类");
        category.setCategoryType(3);
        categoryRepository.save(category);
    }

}
