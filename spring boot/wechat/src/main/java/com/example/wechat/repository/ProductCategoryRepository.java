package com.example.wechat.repository;

import com.example.wechat.dao.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    //通过传入的类型来查询结果
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
