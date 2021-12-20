package com.changgou.search.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.search.service.ESManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ccwu
 * @date 2021/11/17 20:11
 */

@RestController
@RequestMapping("/manager")
public class ESManagerController {

    @Autowired
    private ESManagerService esManagerService;

    /**
     * //创建索引库结构
     * @return
     */
    @GetMapping("/create")
    public Result create(){

        System.out.println("======================");

        esManagerService.createMappingAndIndex();
        return new Result(true, StatusCode.OK, "导入全部数据成功");
    }

    /**
     * 导入全部数据
     */
    @GetMapping("/importAll")
    public Result importAll(){
        esManagerService.importAll();
        return new Result(true, StatusCode.OK,"导入全部数据成功");
    }

}


