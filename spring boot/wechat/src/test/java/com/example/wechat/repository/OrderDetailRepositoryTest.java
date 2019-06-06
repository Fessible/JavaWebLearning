package com.example.wechat.repository;

import com.example.wechat.dao.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository repository;

    @Test
    public void save() {
        OrderDetail detail = new OrderDetail();
        detail.setOrderId("222");
        detail.setDetailId("787878");
        detail.setProductId("123");
        detail.setProductName("呵呵呵呵呵呵");
        detail.setProductPrice(new BigDecimal(111.22));
        detail.setProductQuantity(33);
        repository.save(detail);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> details = repository.findByOrderId("222");
        System.out.println(details);
    }
}