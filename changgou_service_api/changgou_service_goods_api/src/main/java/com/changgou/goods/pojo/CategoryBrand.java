package com.changgou.goods.pojo;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author ccwu
 * @date 2021/10/27 15:50
 */
@Data
@Table(name="tb_category_brand")
public class CategoryBrand implements Serializable {
    @Id
    private Integer categoryId;

    @Id
    private Integer brandId;
}