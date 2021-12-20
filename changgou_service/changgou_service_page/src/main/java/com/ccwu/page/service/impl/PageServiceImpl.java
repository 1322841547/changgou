package com.ccwu.page.service.impl;

import com.alibaba.fastjson.JSON;
import com.ccwu.page.service.PageService;
import com.changgou.entity.Result;
import com.changgou.goods.feign.CategoryFeign;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.feign.SpuFeign;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author ccwu
 * @date 2021/11/24 18:50
 */

@Service
public class PageServiceImpl implements PageService {

    @Value("${page_path}")
    private String pagePath;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private SpuFeign spuFeign;

    @Autowired
    private CategoryFeign categoryFeign;

    @Autowired
    private SkuFeign skuFeign;

    @Override
    public void generateItemPage(String spuId) {
        //1.获取context对象,用于存放商品详情数据
        Context context = new Context();
        /**
         * public void setVariable(Map<String, Object> variables){}
         */
        //获取静态化页面数据，自定义findItemData方法
        Map<String, Object> variables = this.findItemData(spuId);
        context.setVariables(variables);

        //2.获取商品详情页生成的指定位置
        File pagePathDir = new File(pagePath);
        if (! pagePathDir.exists()){
            pagePathDir.mkdir();
        }


        //3.定义输出流,进行文件生成
        Writer contentWriter = null;
        File file = new File(pagePathDir + "/" + spuId + ".html");

        try {
            contentWriter = new PrintWriter(file);
            /**
             * 生成文件
             *    void process(String var1,    模板名称
             *    IContext var2,               context对象,包含了模板需要的数据
             *    Writer var3)                 输出流,指定文件输出位置
             *
             */
            templateEngine.process("item", context, contentWriter);
            System.out.println("完成====生成商品详情页面");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            //关闭流
            try {
                contentWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //4.关闭流

    }

    private Map<String, Object> findItemData(String spuId) {
        Map<String,Object> resultMap = new HashMap<>();

        //获取spu信息
        Result<Spu> spuResult = spuFeign.findSpuById(spuId);
        System.out.println(spuResult+"===========================");
        Spu spu = spuResult.getData();
        resultMap.put("spu",spu);

        //获取图片信息
        if (spu != null){
            if(!StringUtils.isEmpty(spu.getImages())){
                resultMap.put("imageList",spu.getImages().split(","));
            }
        }
        //获取分类信息
        Category category1 = categoryFeign.findById(spu.getCategory1Id()).getData();
        if(category1 == null){
            category1 = categoryFeign.findById(439).getData();
        }
        resultMap.put("category1",category1);


        Category category2 = categoryFeign.findById(spu.getCategory2Id()).getData();
        if(category2 == null){
            category2 = categoryFeign.findById(533).getData();
        }
        resultMap.put("category2",category2);

        Category category3 = categoryFeign.findById(spu.getCategory3Id()).getData();
        if(category3 == null){
            category3 = categoryFeign.findById(539).getData();
        }
        resultMap.put("category3",category3);

        //获取sku集合信息
        List<Sku> skuList = skuFeign.findSkuListBySpuId(spuId);
        resultMap.put("skuList",skuList);

        resultMap.put("specificationList", JSON.parseObject(spu.getSpecItems(), Map.class));
        return resultMap;
    }
}
