package com.example.wechat.service.info;

import com.example.wechat.dao.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImpTest {

    @Autowired
    ProductInfoServiceImp productInfoServiceImp;

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoServiceImp.findOne("123");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findAll() {
        //分页查询
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<ProductInfo> page = productInfoServiceImp.findAll(pageRequest);
        System.out.println(page.getTotalElements());

    }

    @Test
    public void findUpAll() {
        List<ProductInfo> infoList = productInfoServiceImp.findUpAll();
        Assert.assertNotEquals(0, infoList.size());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1245");
        productInfo.setCategoryType(1);
        productInfo.setProductDescription("香味十足");
        productInfo.setProductName("窝窝头");
        productInfo.setProductStatus(0);
        productInfo.setProductPrice(new BigDecimal(23.5));
        productInfo.setProductStock(20);

        ProductInfo result = productInfoServiceImp.save(productInfo);
        Assert.assertNotNull(result);
    }
}