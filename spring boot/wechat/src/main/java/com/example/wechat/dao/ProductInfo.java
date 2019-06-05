package com.example.wechat.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 商品
 */
@Entity
@Data
public class ProductInfo {

    @Id
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 价格
     */
    private BigDecimal productPrice;

    /**
     * 库存
     */
    private Integer productStock;

    /**
     * 描述
     */
    private String productDescription;

    /**
     * 图片
     */
    private String productIcon;

    /**
     * 类目类型
     */
    private Integer categoryType;

    /**
     * 商品状态
     */
    private Integer productStatus;
}
