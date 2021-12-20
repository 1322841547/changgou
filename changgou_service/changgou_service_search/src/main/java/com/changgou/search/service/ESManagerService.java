package com.changgou.search.service;

/**
 * @author ccwu
 * @date 2021/11/17 19:36
 */


public interface ESManagerService {

    /**
     * 1.创建索引库结构
     */
    void createMappingAndIndex();

    /**
     * 2.导入全部数据到ES索引库
     */
    void importAll();

    /**
     * 3. 根据spuId导入数据到ES索引库
     */
    void importDataBySpuId(String spuId);

    /**
     * 4.根据souid删除es索引库中相关的sku数据
     * @param spuId
     */
    void deleteBySpuId(String spuId);
}
