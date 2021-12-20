package com.changgou.goods.dao;

import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author ccwu
 * @date 2021/10/21 18:28
 *
 * 继承了Mapper接口，就自动实现了增删改查的常用方法。
 */
@Repository
public interface BrandMapper extends Mapper<Brand> {


/**
 * 根据分类名称查询品牌的name、image
 * @param categoryName
 * @return
 */
@Select("SELECT name,image FROM tb_brand WHERE id  IN (" +
        "SELECT brand_id FROM tb_category_brand WHERE  category_id IN (" +
        "SELECT id FROM tb_category WHERE NAME=#{name}) ) order by seq")
List<Brand> findListByCategoryName(@Param("name") String categoryName);

}
