package com.example.wechat.service.info;

import com.example.wechat.dao.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductInfoService {
    ProductInfo findOne(String productId);

    //分页查询
    Page<ProductInfo> findAll(Pageable pageable);

    //查找所有上架的商品
    List<ProductInfo> findUpAll();

    ProductInfo save(ProductInfo productInfo);
}
