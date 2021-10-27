package com.changgou.goods.pojo;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * @author ccwu
 * @date 2021/10/26 20:21
 */

@Data
public class Goods implements Serializable {
    @Id
    private Spu spu;

    @Id
    private List<Sku> skuList;
}
