package com.ccwu.listener;

import okhttp3.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author ccwu
 * @date 2021/11/5 15:48
 *
 * rabbitmq消费者， 生产者在 changgou_canal ： com.changgou.canal.listener.BusinessListener
 */

@Component
public class AdListener {

    @RabbitListener(queues = "ad_update_queue")
    public void receiveMessage(String message){    // 传入参数 Message message 或者 String message
        //System.out.println(message);

        /**
         * 使用nginx+Lua+redis实现广告缓存
         * OpenResty，基于 NGINX 的可伸缩的 Web 平台，相当于封装了nginx,并且集成了LUA脚本
         *
         * 1.nginx作为反向代理，让redis根据position去读取数据库
         * 2.nginx实现缓存预热，参数为广告位置position(传入message)
         * 3.执行ad_load.lua脚本 ，实现连接mysql 查询数据 并存储到redis中。
         */
        //发起远程调用nginx
        String url = "http://192.168.200.128/ad_update?position=" + message;
        Request request = new Request.Builder().url(url).build();
        Call call = new OkHttpClient().newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求成功
                System.out.println("请求成功:"+response.message());
            }
        });
    }
}
