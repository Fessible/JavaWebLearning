package com.example.wechat.dto;

import com.example.wechat.dao.OrderDetail;
import com.example.wechat.enums.OrderStatusEnum;
import com.example.wechat.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * api中对应的值
 */
@Data
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerAddress;

    private String buyerPhone;

    /**
     * 微信号
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态
     */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

    List<OrderDetail> detailList;

}
