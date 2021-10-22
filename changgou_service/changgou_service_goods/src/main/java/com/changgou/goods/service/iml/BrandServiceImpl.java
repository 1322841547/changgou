package com.changgou.goods.service.iml;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author ccwu
 * @date 2021/10/21 18:33
 */

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 1.查询所有商品
     * @return
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }


    /**
     * 2.根据ID查询
     * @param id
     * @return
     */
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }


    /**
     * 3.新增品牌
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        brandMapper.insertSelective(brand);
    }


    /**
     * 4.更新品牌
     * @param brand
     */
    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }


    /**
     * 5.根据id删除品牌
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }


    /**
     * 6.多条件搜索品牌查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Brand> findList(Map<String, Object> searchMap) {

        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        if (searchMap != null) {

            String brandColumn = "name";     //数据库品牌的列名为“name”
            Object brandName = searchMap.get(brandColumn);   //传入的参数为Object类型的模糊品牌名"brandName"

            String initialColumn = "letter";   //数据库品牌的首字母的列名为“letter”
            Object initialName = searchMap.get(initialColumn);


            //1.按照品牌名称模糊查询
            if (brandName != null && !"".equals(brandName)) {
                criteria.andLike(brandColumn, "%" + brandName + "%");
            }

            //2.按照品牌首字母精确查询
            if (initialName != null && !"".equals(initialName)) {
                criteria.andEqualTo(initialColumn, initialName);
            }

        }

        return brandMapper.selectByExample(example);
    }


    /**
     * 7.分页查询所有
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Brand> findPage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Brand>) brandMapper.selectAll();
    }


    /**
     * 8.多条件分页品牌查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Brand> findListPage(Map<String, Object> searchMap, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Brand> list = findList(searchMap);
        return (Page<Brand>) list;
    }


    /**
     * 9.根据分类名称查询品牌的name、image
     * @param categoryName
     * @return
     */
    @Override
    public List<Brand> findListByCategoryName(String categoryName) {
        return brandMapper.findListByCategoryName(categoryName);
    }
}