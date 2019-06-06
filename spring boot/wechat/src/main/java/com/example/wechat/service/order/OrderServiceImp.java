package com.example.wechat.service.order;

import com.example.wechat.dto.OrderDTO;
import com.example.wechat.repository.OrderMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    OrderMasterRepository repository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        //1.查询商品

        //2.计算总价

        //3.写入订单数据库

        //4.扣库存

        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTOl) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
