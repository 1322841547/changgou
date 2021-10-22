package com.changgou.goods.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author ccwu
 * @date 2021/10/22 21:35
 */

@RestController
@RequestMapping("/spec")

public class SpecController {

    @Autowired
    private SpecService specService;

    @GetMapping("/category/{categoryName}")
    public Result findListByCategoryName(@PathVariable("categoryName") String categoryName){

        List<Map> specList = specService.findListByCategoryName(categoryName);
        return new Result(true, StatusCode.OK,"",specList);

    }
}
