package kr.co.polycube.backendtest.log;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* kr.co.polycube.backendtest.controller.*Controller.*(..))")
    public void logBefore() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("Request Time : " + System.currentTimeMillis());
        System.out.println("Client Agent : " + request.getHeader("User-Agent"));
    }

    @AfterReturning(pointcut = "execution(* kr.co.polycube.backendtest.controller.*Controller.*(..))", returning = "result")
    public void logAfterReturning(Object result) {
        System.out.println("Method Returned Value : " + result);
    }
}
