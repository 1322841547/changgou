package com.changgou.goods.handler;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ccwu
 * @date 2021/10/22 15:49
 *
 * 统一异常处理
 */

@ControllerAdvice   //声明该类是一个增强类
public class BaseExceptionHandler {

    @ResponseBody  //当前的方法的返回值以json格式返回
    @ExceptionHandler(value = Exception.class)   //对什么异常类进行处理
    public Result error( Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
