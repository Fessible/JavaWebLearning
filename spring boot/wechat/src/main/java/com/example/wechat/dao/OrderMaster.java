package com.example.wechat.dao;

import com.example.wechat.enums.OrderStatusEnum;
import com.example.wechat.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

//
@Entity
@Data
//@EntityListeners(AuditingEntityListener.class)
public class OrderMaster {

    @Id
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

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;

}
