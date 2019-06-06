package com.example.wechat.repository;

import com.example.wechat.dao.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    OrderMasterRepository repository;

    private String OPEN_ID = "3456";

    @Test
    public void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerAddress("三里屯的某个村里面");
        orderMaster.setBuyerName("我的朋友");
        orderMaster.setBuyerOpenid(OPEN_ID);
        orderMaster.setBuyerPhone("1233546");
        orderMaster.setOrderAmount(new BigDecimal(100.22));
        orderMaster.setOrderId("222");
//        orderMaster.setCreateTime(new Date(System.currentTimeMillis()));
//        orderMaster.setUpdateTime(new Date(System.currentTimeMillis()));

//        System.out.println(new Date(System.currentTimeMillis()));
        repository.save(orderMaster);
    }

    @Test
    public void byBuyerOpenid(){
        PageRequest request = new PageRequest(0, 5);

        Page<OrderMaster> byBuyerOpenid = repository.findByBuyerOpenid(OPEN_ID, request);
        System.out.println(byBuyerOpenid.getTotalElements());

    }

}