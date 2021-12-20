package com.ccwu.page.listenner;

import com.ccwu.page.service.PageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ccwu
 * @date 2021/11/24 19:29
 */

@Component
public class PageListener {

    @Autowired
    private PageService pageService;

    @RabbitListener(queues = "page_create_queue")
    public void receiveMessage(String spuId){
        System.out.println("生成商品详情页面,商品id为: "+spuId);

        //生成静态化页面
        pageService.generateItemPage(spuId);
    }
}
