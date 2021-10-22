package com.changgou.goods.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author ccwu
 * @date 2021/10/22 21:33
 */

public interface SpecService {

    /**
     * 1.根据商品分类名称查询规格列表
     * @param categoryName
     * @return
     */
    List<Map> findListByCategoryName(String categoryName);
}
