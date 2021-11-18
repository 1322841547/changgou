package com.changgou.search;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author ccwu
 * @date 2021/11/17 17:28
 *
 * tb_sku索引库实体类
 */

@Document(indexName = "skuinfo", type = "docs")   //标注在实体类上，类似于hibernate的entity注解，标明由mongo来维护该表。
public class SkuInfo implements Serializable {


}
