package com.changgou.goods.dao;

import com.changgou.goods.pojo.Category;
import com.changgou.goods.pojo.Sku;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author ccwu
 * @date 2021/10/26 21:00
 */

@Repository
public interface CategoryMapper extends Mapper<Category> {
}
