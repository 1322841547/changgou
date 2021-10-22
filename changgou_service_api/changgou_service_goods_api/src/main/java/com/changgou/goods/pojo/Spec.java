package com.changgou.goods.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ccwu
 * @date 2021/10/22 21:13
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_spec")  //（ 规格表）
public class Spec {
    @Id
    private Integer id;  // ID
    private String name;  //名称
    private String options;//规格选项
    private Integer seq;//排序
    private Integer template_id;//模板ID
}
