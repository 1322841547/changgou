package com.changgou.goods.service;

/**
 * @author ccwu
 * @date 2021/10/21 18:31
 */

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;


public interface BrandService {

    /**
     * 1.查询所有商品
     */
     List<Brand> findAll();


    /**
     * 2.根据ID查询
     * @param id
     */
     Brand findById(Integer id);


    /**
     * 3.新增品牌
     * @param brand
     */
     void add(Brand brand);


    /**
     * 4.更新品牌
     * @param brand
     */
    void update(Brand brand);


    /**
     * 5.根据id删除品牌
     * @param id
     */
    void deleteById(Integer id);


    /**
     * 6.多条件搜索品牌查询
     * @param searchMap
     * @return
     */
    List<Brand> findList(Map<String, Object>searchMap);


    /**
     * 7.分页查询所有
     * @param page
     * @param size
     * @return
     */
    Page<Brand> findPage(Integer page, Integer size);


    /**
     * 8.多条件分页品牌查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Brand> findListPage(Map<String, Object>searchMap, Integer page, Integer size);

    /**
     * 9.根据分类名称查询品牌的name、image
     * @param categoryName
     * @return
     */
    List<Brand> findListByCategoryName(String categoryName);
}
