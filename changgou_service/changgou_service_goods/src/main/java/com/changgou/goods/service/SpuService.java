package com.changgou.goods.service;

import com.changgou.goods.pojo.Goods;
import com.changgou.goods.pojo.Spu;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SpuService {

    /***
     * 查询所有
     * @return
     */
    List<Spu> findAll();

//    /**
//     * 根据ID查询
//     * @param id
//     * @return
//     */
//    Spu findById(String id);

    /**
     * 根据spuID查询商品
     * @param id
     * @return
     */
    Goods findGoodsById(String id);


    /***
     * 新增
     * @param goods
     */
    void add(Goods goods);

    /***
     * 修改
     * @param goods
     */
    void update(Goods goods);

    /***
     * 删除
     * @param id
     */
    void delete(String id);

    /**
     * 恢复数据
     * @param id
     */
    void restore(String id);

    /***
     * 多条件搜索
     * @param searchMap
     * @return
     */
    List<Spu> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Spu> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Spu> findPage(Map<String, Object> searchMap, int page, int size);

    /**
     * 1.商品审核
     * @param id
     */
    void audit(String id);

    /**
     * 2.下架商品
     * @param id
     */
    void pull(String id);

    /**
     * 3.上架商品
     * @param id
     */
    void put(String id);

    /**
     * 4.物理删除
     * @param id
     */
    void realDel(String id);

}
