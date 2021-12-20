package com.changgou.goods.controller;

import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ccwu
 * @date 2021/10/21 17:23
 */

@CrossOrigin   //开启跨域操作
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;
    /**
     * 1.查询所有商品
     * @return
     */
    @GetMapping
    public Result findAll(){
        List<Brand> brandList = brandService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",brandList);
    }

    /**
     * 2.根据ID查询
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Result findById(@PathVariable("id") int id){
        Brand brand = brandService.findById(id);
        return new Result(true,StatusCode.OK,"查询成功",brand);
    }


    /**
     * 3.新增品牌
     * @param brand
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
        return new Result(true,StatusCode.OK,"添加成功");
    }


    /**
     *  4.更新品牌
     * @param brand
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Brand brand, @PathVariable("id") Integer id){
        brand.setId(id);
        brandService.update(brand);
        return new Result(true,StatusCode.OK,"修改成功");
    }


    /**
     * 5.根据id删除品牌
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
        brandService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }


    /**
     * 6.多条件搜索品牌查询
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap){
        List list = brandService.findList(searchMap);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }


    /**
     * 7.分页查询所有
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/{page}/{size}")
    public Result findPage(@PathVariable("page") int page, @PathVariable("size") int size){
        Page<Brand> pageList = brandService.findPage(page,size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return new Result(true, StatusCode.OK,"查询成功",pageResult);
    }


    /**
     * 8.多条件分页品牌查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result findListPage(@RequestParam Map searchMap, @PathVariable("page") int page, @PathVariable("size")int size){
//      page = 1/0;
        Page<Brand> pageList = brandService.findListPage(searchMap, page, size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return new Result(true,StatusCode.OK, "查询成功", pageResult);
    }


    /**
     * 9.根据分类名称查询品牌的name、image
     * @param categoryName
     * @return
     */
    @GetMapping(value = "/category/{categoryName}")
    public Result findListByCategoryName(@PathVariable("categoryName") String categoryName){
        List<Brand> brandList = brandService.findListByCategoryName(categoryName);
        return new Result(true, StatusCode.OK,"查询成功",brandList);
    }

}
