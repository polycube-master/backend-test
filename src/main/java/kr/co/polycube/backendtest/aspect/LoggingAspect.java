package kr.co.polycube.backendtest.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* kr.co.polycube.backendtest.controller.UserController.*(..))")
    public void logRequest() {
        System.out.println("Client Agent: " + getClientAgent());
    }

    private String getClientAgent() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("User-Agent");
    }
}
