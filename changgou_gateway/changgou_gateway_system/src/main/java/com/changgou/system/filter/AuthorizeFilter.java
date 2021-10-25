package com.changgou.system.filter;

import com.changgou.system.util.JWTUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author ccwu
 * @date 2021/10/25 12:12
 *
 * 鉴权过滤器，验证Token
 */

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    private static final String AUTHORIZE_TOKEN = "token";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        System.out.println("经过第3个过滤器：Token验证");
        ServerHttpRequest request = exchange.getRequest();  //获取请求
        ServerHttpResponse response = exchange.getResponse();  // 获取响应
        //如果是登录则放行（账号密码登录。无需token）
        if (request.getURI().getPath().contains("/admin/login")){
            return chain.filter(exchange);
        }
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);   //获取客户端请求的token值
        //token为空时
        if (StringUtils.isEmpty(token)){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //token存在，进行校正
        try {
            JWTUtil.parseJWT(token);
        }catch (Exception e){
            e.printStackTrace();
            //10. 解析jwt令牌出错, 说明令牌过期或者伪造等不合法情况出现
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //11. 返回
            return response.setComplete();
        }

        //12. 放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 3;
    }
}
