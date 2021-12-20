package com.changgou.search.controller;

import com.changgou.entity.Page;
import com.changgou.search.pojo.SkuInfo;
import com.changgou.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/list")
    public String search(@RequestParam Map<String,String> searchMap, Model model){
        //特殊符号处理
        this.handleSearchMap(searchMap);

        //执行查询返回
        Map<String,Object> resultMap = searchService.search(searchMap);

        model.addAttribute("searchMap", searchMap);
        model.addAttribute("result", resultMap);

        //封装分页数据并返回
        //1.总记录数
        //2.当前页
        //3.每页显示多少条
        Page<SkuInfo> page = new Page<SkuInfo>(
                Long.parseLong(String.valueOf( resultMap.get("total"))),
                Integer.parseInt(String.valueOf(resultMap.get("pageNum"))),
                Page.pageSize
        );
        model.addAttribute("page",page);

        //将当前URL存入到Model中，以便前端页面后续拼接
        addUrl(searchMap,model);

        return "search";
    }




    @GetMapping
    @ResponseBody    //表示返回结果到页面，否则return 就是返回某个页面
    public Map search(@RequestParam Map<String,String> searchMap){
        //特殊符号处理
        this.handleSearchMap(searchMap);
        Map searchResult = searchService.search(searchMap);
        return searchResult;
    }

    /**
     * 特殊符号处理
     * @param searchMap
     */
    private void handleSearchMap(Map<String, String> searchMap) {
        Set<Map.Entry<String, String>> entries = searchMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            if (entry.getKey().startsWith("spec_")){
                searchMap.put(entry.getKey(),entry.getValue().replace("+","%2B"));
            }
        }
    }

    /**
     * 拼接组装出上次搜索的URL，
     * 然后每次将URL存入到Model中
     */
    public void addUrl(Map<String,String> searchMap, Model model){

        StringBuilder url = new StringBuilder("/search/list");
        if(searchMap != null && searchMap.size()!=0){
            url.append("?");
            for (String paramKey  : searchMap.keySet()) {
                if (!"sortRule".equals(paramKey) && !"sortField".equals(paramKey) && !"pageNum".equals(paramKey)){
                    url.append(paramKey).append("=").append(searchMap.get(paramKey)).append("&");
                }
            }
            String urlString = url.toString();
            //去除路径上的最后一个&
            urlString = urlString.substring(0, urlString.length() - 1);
            model.addAttribute("url", urlString);

        }else {
            model.addAttribute("url", url);
        }
    }
}
