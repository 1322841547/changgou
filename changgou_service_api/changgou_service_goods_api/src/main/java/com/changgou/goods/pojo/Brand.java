package com.changgou.goods.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author ccwu
 * @date 2021/10/21 18:10
 *
 * @Table和@Id都是JPA注解，@Table用于配置表与实体类的映射关系，
 * @Id用于标识主键属性。
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_brand")   //品牌表
public class Brand implements Serializable {
    @Id
    private Integer id;//品牌id
    private String name;//品牌名称
    private String image;//品牌图片地址
    private String letter;//品牌的首字母
    private Integer seq;//排序
}
