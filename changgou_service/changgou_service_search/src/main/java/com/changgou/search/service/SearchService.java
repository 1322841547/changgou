package com.changgou.search.service;

import java.util.Map;

/**
 * @author ccwu
 * @date 2021/11/22 16:38
 */


public interface SearchService {

    /**
     * 全文检索
     * @param paraMap  查询参数
     * @return
     */
    Map search(Map<String, String> paraMap);
}
