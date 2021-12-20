package com.changgou.search.service.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.changgou.search.dao.ESManagerMapper;


import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.search.pojo.SkuInfo;
import com.changgou.search.service.ESManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ccwu
 * @date 2021/11/17 19:37
 */
@Service
public class ESManagerServiceImpl implements ESManagerService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private ESManagerMapper esManagerMapper;

    /**
     * 创建索引库结构
     */
    @Override
    public void createMappingAndIndex() {

        //创建索引
        elasticsearchTemplate.createIndex(SkuInfo.class);
        //创建映射
        elasticsearchTemplate.putMapping(SkuInfo.class);
    }

    /**
     * 导入全部
     */
    @Override
    public void importAll() {

        List<Sku> skuList = skuFeign.findSkuListBySpuId("all");
        if (skuList == null || skuList.size() <= 0){
            throw new RuntimeException("当前没有数据被查询到,无法导入索引库");
        }

        //skuList集合转换为json字符串
        String jsonSkuList = JSON.toJSONString(skuList);
        List<SkuInfo> skuInfoList = JSON.parseArray(jsonSkuList, SkuInfo.class);
        //将规格信息转换为map
        for (SkuInfo skuInfo : skuInfoList) {
            skuInfo.setSpecMap(JSON.parseObject(skuInfo.getSpec(), Map.class));
        }
        System.out.println(skuInfoList.toString());
        //导入索引库
        esManagerMapper.saveAll(skuInfoList);

    }

    /**
     * 根据skuId导入
     * @param spuId
     */
    @Override
    public void importDataBySpuId(String spuId) {

        List<Sku> skuList = skuFeign.findSkuListBySpuId(spuId);

        if (skuList == null || skuList.size() <= 0){
           throw new RuntimeException("当前没有数据被查询到,无法导入索引库");
//            System.out.println("无数据-====================");
        }else {

            System.out.println("导入索引库====");
            //skuList集合转换为json字符串
            String jsonSkuList = JSON.toJSONString(skuList);
            List<SkuInfo> skuInfoList = JSON.parseArray(jsonSkuList, SkuInfo.class);

            //将规格信息转换为map
            for (SkuInfo skuInfo : skuInfoList) {
                skuInfo.setSpecMap(JSON.parseObject(skuInfo.getSpec(), Map.class));
            }

            //导入索引库
            esManagerMapper.saveAll(skuInfoList);
        }
    }

    /**
     * 根据souid删除es索引库中相关的sku数据
     * @param spuId
     */
    @Override
    public void deleteBySpuId(String spuId) {

        List<Sku> skuList = skuFeign.findSkuListBySpuId(spuId);
        if (skuList.size() == 0 || skuList == null) throw new RuntimeException("当前没有数据被查询到,无法删除对应的索引库");

        System.out.println("删除索引库====");
        for (Sku sku : skuList) {
            esManagerMapper.deleteById(Long.parseLong(sku.getId()));
        }

    }
}
