package com.example.wechat.service.catecory;

import com.example.wechat.dao.ProductCategory;

import java.util.List;

/**
 * 类目
 * 管理使用
 */
public interface ProductCategoryService {

    void findOneById(Integer id);

    void findAll();

    ProductCategory save(ProductCategory productCategory);

    List<ProductCategory> findByCategoryIn(List<Integer> categoryList);

}
