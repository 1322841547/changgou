package com.ccwu.page.service;

/**
 * @author ccwu
 * @date 2021/11/24 18:26
 */


public interface PageService {

    /**
     *
     * 根据监听到的spuId
     * 生成静态化界面
     *
     */
    void generateItemPage(String spuId);
}
