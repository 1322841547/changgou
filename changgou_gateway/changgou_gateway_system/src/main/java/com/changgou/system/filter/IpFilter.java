package com.changgou.system.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

/**
 * @author ccwu
 * @date 2021/10/24 15:56
 *
 * 获取客户端的IP进行过滤
 */

@Component
public class IpFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("第一个过滤器：IP过滤");
        //获取客户端访问的IP
        ServerHttpRequest request = exchange.getRequest();
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        //具体的业务处理后放行
        if (remoteAddress != null) {
//            System.out.println("客户端ip为：" + remoteAddress.getHostName());
            return chain.filter(exchange);
        }else {
            return null;
        }

    }

    @Override
    public int getOrder() {
        return 1;
    }

}
