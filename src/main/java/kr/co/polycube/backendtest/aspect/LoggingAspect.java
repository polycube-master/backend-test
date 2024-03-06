package kr.co.polycube.backendtest.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    public LoggingAspect(HttpServletRequest request) {
    }

    @Pointcut("execution(* kr.co.polycube.backendtest.controller.UserController.createUser(..))" +
            " || execution(* kr.co.polycube.backendtest.controller.UserController.getUserById(..))" +
            " || execution(* kr.co.polycube.backendtest.controller.UserController.updateUser(..))")
    public void userApiPointcut() {}

    // 반환값 void는 지양
    @Before("userApiPointcut()")
    public void logUserAgent() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        String userAgent = request.getHeader("User-Agent");
        System.out.println("Client User-Agent: " + userAgent);
    }
}
