package com.example.wechat.repository;


import com.example.wechat.dao.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    ProductInfoRepository repository;

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("124");
        productInfo.setCategoryType(1);
        productInfo.setProductDescription("香味十足");
        productInfo.setProductName("南方黑芝麻糊");
        productInfo.setProductStatus(0);
        productInfo.setProductPrice(new BigDecimal(23.5));
        productInfo.setProductStock(20);

        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> infoList = repository.findByProductStatus(0);
        Assert.assertNotEquals(0, infoList.size());
    }

}