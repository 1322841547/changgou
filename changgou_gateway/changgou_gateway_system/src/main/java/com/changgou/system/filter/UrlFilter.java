package com.changgou.system.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @author ccwu
 * @date 2021/10/24 15:59
 *
 * 获取客户端的url进行过滤
 */

@Component
public class UrlFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("经过第二个过滤器：URL过滤");

        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
//        System.out.println(request.getURI());
//        System.out.println("请求path为:" + path);
        //具体的业务处理后放行
        if(path != null){
            return chain.filter(exchange);
        }else {
            return null;
        }
    }



    @Override
    public int getOrder() {
        return 2;
    }
}
