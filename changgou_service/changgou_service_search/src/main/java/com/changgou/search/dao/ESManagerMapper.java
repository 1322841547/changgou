package com.changgou.search.dao;

import com.changgou.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * @author ccwu
 * @date 2021/11/17 19:34
 */

public interface ESManagerMapper extends ElasticsearchRepository<SkuInfo,Long> {
}
