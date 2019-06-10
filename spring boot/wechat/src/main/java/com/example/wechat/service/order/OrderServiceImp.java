package com.example.wechat.service.order;

import com.example.wechat.convert.OrderMasterToOrderDTO;
import com.example.wechat.dao.OrderDetail;
import com.example.wechat.dao.OrderMaster;
import com.example.wechat.dao.ProductInfo;
import com.example.wechat.dto.CartDTO;
import com.example.wechat.dto.OrderDTO;
import com.example.wechat.enums.OrderStatusEnum;
import com.example.wechat.enums.PayStatusEnum;
import com.example.wechat.enums.ResultEnum;
import com.example.wechat.exception.SellException;
import com.example.wechat.repository.OrderDetailRepository;
import com.example.wechat.repository.OrderMasterRepository;
import com.example.wechat.service.info.ProductInfoService;
import com.example.wechat.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImp implements OrderService {

    @Autowired
    OrderMasterRepository repository;

    @Autowired
    ProductInfoService service;

    @Autowired
    OrderDetailRepository detailRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal amount = new BigDecimal(0);

        //1.查询商品的数量价格
        for (OrderDetail orderDetail : orderDTO.getDetailList()) {
            ProductInfo productInfo = service.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }

            //2.计算总价
            amount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(amount);

            //保存订单详情
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);

            //从productInfo获取图片
            detailRepository.save(orderDetail);

        }

        //3.写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(amount);
        repository.save(orderMaster);

        //4.扣库存
        List<CartDTO> cartDTOList = orderDTO.getDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());

        service.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        Optional<OrderMaster> optionalOrderMaster = repository.findById(orderId);
        if (!optionalOrderMaster.isPresent()) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }

        OrderMaster orderMaster = optionalOrderMaster.get();

        List<OrderDetail> details = detailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(details)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIT);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setDetailList(details);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> page = repository.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDTO> orderDTOList = OrderMasterToOrderDTO.convert(page.getContent());

        Page<OrderDTO> dtoPage = new PageImpl<OrderDTO>(orderDTOList, pageable, page.getTotalElements());

        return dtoPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();

        //判断订单状态
        //如果是新创建的订单，可以取消
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("取消订单，订单状态不正确，orderStatus={}", orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster update = repository.save(orderMaster);
        if (update == null) {
            log.error("取消订单，更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAILURE);
        }

        if (CollectionUtils.isEmpty(orderDTO.getDetailList())) {
            log.error("取消订单，订单中无商品详情，orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        //返还库存
        List<CartDTO> cartDTOS = orderDTO.getDetailList()
                .stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());

        service.increaseStock(cartDTOS);

        //如果已支付，需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //todo
        }

        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTOl) {
        //判断订单状态
        if (!orderDTOl.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("完结订单 订单状态不正确，orderId={}", orderDTOl.getOrderId());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTOl.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTOl, orderMaster);
        OrderMaster updateResult = repository.save(orderMaster);

        if (updateResult == null) {
            log.error("订单更新失败，orderMaster=[]", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAILURE);
        }
        return orderDTOl;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
