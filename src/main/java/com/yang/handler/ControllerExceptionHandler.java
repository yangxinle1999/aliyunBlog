package com.yang.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//统一拦截所有exception，并做处理
@ControllerAdvice//会拦截所有标注controller注解的控制器
public class ControllerExceptionHandler {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());//作为日志记录异常信息，并打印在控制台
    @ExceptionHandler(Exception.class) //标明这个方法用于拦截Exception级别的异常
    public ModelAndView exceptionHander(HttpServletRequest request,Exception e){
        logger.error("Request URL : {}, Exception : {}",request.getRequestURI(),e);//在日志中记录异常信息
        ModelAndView mv=new ModelAndView();
        mv.addObject("url",request.getRequestURI());
        mv.addObject("exception",e);
        mv.setViewName("error/error"); //设置返回的页面
        return mv;
    }
}

