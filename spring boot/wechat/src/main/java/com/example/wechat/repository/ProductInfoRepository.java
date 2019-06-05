package com.example.wechat.repository;

import com.example.wechat.dao.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {

    /**
     * 上架商品
     */
    List<ProductInfo> findByProductStatus(Integer status);

    ProductInfo findByProductId(String productId);
}
