package kr.co.polycube.backendtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


// 필터 적용하려면 @SpringBootApplication 이 붙은 클래스에 @ServletComponentScan 을 선언
@SpringBootApplication
public class BackendTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                BackendTestApplication.class,
                args
        );
    }

}
