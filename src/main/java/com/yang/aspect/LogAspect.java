package com.yang.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect //指明这是一个切面类
@Component //开启组件扫描
public class LogAspect {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());//作为日志记录信息，并打印在控制台
    @Pointcut("execution(* com.yang.controller.*.*(..))") //@Pointcut声明这是一个切面,execution规定拦截的类
    public void log(){};

    @Before("log()") //在切面log()之前执行该方法
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        String url=request.getRequestURL().toString();//得到访问路径
        String ip=request.getRemoteAddr();//得到ip地址
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();//得到拦截的方法名
        Object[] args=joinPoint.getArgs();//得到请求的参数
        RequestLog requestLog=new RequestLog(url,ip,classMethod,args);
        logger.info("request : {}",requestLog);//记录的请求
    }

    @After("log()") //在切面log()之后执行该方法
    public void doAfter(){
        logger.info("----------doAfter-----------");
    }

    //在切面拦截的方法返回之后即return之后执行该方法，用result来接受返回值
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterRuturn(Object result){
        logger.info("result : {}",result);
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }
    }

}
