package com.changgou.goods.service.iml;

import com.changgou.goods.dao.SpecMapper;
import com.changgou.goods.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ccwu
 * @date 2021/10/22 21:33
 */

@Service
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecMapper specMapper;

    /**
     * 1.根据商品分类名称查询规格列表
     * @param categoryName
     * @return
     */
    @Override
    public List<Map> findListByCategoryName(String categoryName) {
        List<Map> specList = specMapper.findListByCategoryName(categoryName);

        //对specList的”options”进行分割组成数组
        for (Map spec : specList) {
            String[] options = spec.get("options").toString().split(",");
            spec.put("options", options);
        }
        return specList;
    }
}
