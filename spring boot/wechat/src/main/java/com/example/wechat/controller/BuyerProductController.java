package com.example.wechat.controller;

import com.example.wechat.VO.ProductInfoVo;
import com.example.wechat.VO.ProductVO;
import com.example.wechat.VO.ResultVO;
import com.example.wechat.dao.ProductCategory;
import com.example.wechat.dao.ProductInfo;
import com.example.wechat.service.catecory.ProductCategoryService;
import com.example.wechat.service.info.ProductInfoService;
import com.example.wechat.service.info.ProductInfoServiceImp;
import com.example.wechat.utils.ResultUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    ProductInfoService service;

    @Autowired
    ProductCategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //1.查询所有上架商品
        List<ProductInfo> productInfoList = service.findUpAll();
//        List<Integer> typeList = new ArrayList<>();

        //2.一次性查询类目

        //传统写法
//        for (ProductInfo productInfo : productInfoList) {
//            typeList.add(productInfo.getCategoryType());
//        }

        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryIn(categoryTypeList);

        //拼接数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {

                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    //使用BeanUtils帮助我们快速地给对象赋值
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVO.setProductInfoVoList(productInfoVoList);
            productVOList.add(productVO);
        }

        return ResultUtils.success(productVOList);
    }
}
