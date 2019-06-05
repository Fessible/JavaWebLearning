package com.example.wechat.service.catecory;

import com.example.wechat.dao.ProductCategory;
import com.example.wechat.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImp implements ProductCategoryService {

    @Autowired
    ProductCategoryRepository repository;

    @Override
    public void findOneById(Integer id) {
        repository.findById(id);
    }

    @Override
    public void findAll() {
        repository.findAll();
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }


    @Override
    public List<ProductCategory> findByCategoryIn(List<Integer> categoryList) {
        return repository.findByCategoryTypeIn(categoryList);
    }
}
