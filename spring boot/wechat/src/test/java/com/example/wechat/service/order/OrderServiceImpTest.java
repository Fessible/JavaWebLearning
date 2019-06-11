package com.example.wechat.service.order;

import com.example.wechat.dao.OrderDetail;
import com.example.wechat.dto.OrderDTO;
import com.example.wechat.enums.OrderStatusEnum;
import com.example.wechat.enums.PayStatusEnum;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImpTest {
    private String openId = "110110";

    @Autowired
    OrderServiceImp orderServiceImp;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("武当山");
        orderDTO.setBuyerOpenid(openId);
        orderDTO.setBuyerName("小明");
        orderDTO.setBuyerPhone("4477545");

        //购物车
        List<OrderDetail> detailList = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123");
        orderDetail.setProductQuantity(5);

        detailList.add(orderDetail);
        orderDTO.setDetailList(detailList);

        OrderDTO orderDTO1 = orderServiceImp.create(orderDTO);
        System.out.println(orderDTO1);
    }

    @Test
    public void findOne() {
        OrderDTO one = orderServiceImp.findOne("156014548386561599");
        System.out.println(one);
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderDTO> list = orderServiceImp.findList(openId, pageRequest);
        Assert.assertNotEquals(0, list.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO one = orderServiceImp.findOne("156014548386561599");
        orderServiceImp.cancel(one);
        System.out.println(one);
    }

    @Test
    public void finish() {
        OrderDTO one = orderServiceImp.findOne("156014566952994437");
        OrderDTO finish = orderServiceImp.finish(one);
        Integer code = OrderStatusEnum.FINISH.getCode();
        Assert.assertEquals(code, finish.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO one = orderServiceImp.findOne("156014570146694036");
        OrderDTO paid = orderServiceImp.paid(one);
        Integer code = PayStatusEnum.SUCCESS.getCode();
        Assert.assertEquals(code,paid.getPayStatus());
    }
}