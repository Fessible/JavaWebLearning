package com.example.wechat;

import com.example.wechat.dao.ProductCategory;
import com.example.wechat.repository.ProductCategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
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
    public void findOneTest() {
        Optional<ProductCategory> productCategory = categoryRepository.findById(1);
        if (productCategory.isPresent()) {
            System.out.println(productCategory);
        }
    }

    @Test
    @Transactional //不希望把数据添加到数据库中，实现完测试后就进行回滚
    public void save() {
        ProductCategory category = new ProductCategory();
        category.setCategoryName("男生最爱");
        category.setCategoryType(4);
        ProductCategory category1 = categoryRepository.save(category);
        Assert.assertNotNull(category1);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(0, 1,2);
        List<ProductCategory> productCategories = categoryRepository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, productCategories.size());
    }

    @Test
    public void update() {
        ProductCategory category = new ProductCategory();
        category.setCategoryId(4);
        category.setCategoryName("食品类");
        category.setCategoryType(20);
        categoryRepository.save(category);
    }
}
