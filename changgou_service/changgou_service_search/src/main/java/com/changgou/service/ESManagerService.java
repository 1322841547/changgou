package com.changgou.service;

/**
 * @author ccwu
 * @date 2021/11/17 19:36
 */
public interface ESManagerMapper {

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
    void importDataBySpuId();
}
