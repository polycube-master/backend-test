package kr.co.polycube.backendtest.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/* UserService 클래스의 특정 메소드가 호출될 때마다 클라이언트 에이전트 정보를 출력하는 간단한 로깅 Aspect를 구현합니다. */
@Aspect // Spring AOP 의 어노테이션,  해당 클래스를 Aspect 로 지정
@Component // 빈 등록
public class LoggingAspect {

    @Before("execution(* kr.co.polycube.backendtest.service.UserService.saveUser(..)) || " +
            "execution(* kr.co.polycube.backendtest.service.UserService.getUserById(..)) || " +
            "execution(* kr.co.polycube.backendtest.service.UserService.updateUser(..))")
    public void logClientAgent(JoinPoint joinPoint){
        Object[] args=joinPoint.getArgs(); //메소드의 매개변수 가져오기
        if(args!=null && args.length>0){
            for(Object arg: args){
                // 매개변수가 HttpServletRequest 객체인지 확인
                if(arg instanceof HttpServletRequest){
                    HttpServletRequest request=(HttpServletRequest) arg;
                    String clientAgent=request.getHeader("User-Agent"); // 클라이언트 에이전트 가져오기
                    System.out.println("Client Agent: "+ clientAgent);

                }
            }
        }
    }
}
