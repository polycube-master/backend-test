package kr.co.polycube.backendtest.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@Order(1) // 가장 먼저 실행되도록 설정
public class SpecialCharacterFilter implements Filter {
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
       log.info("===필터 동작=====");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        log.info("filter 시작입니다.");
        chain.doFilter(httpRequest,httpResponse);
        log.info("filter 끝입니다.");
//


        // 특수 문자 검사
        if (containsSpecialCharacter(requestURI)) {

            System.out.println("특수문자 발견");
            // 특수 문자가 포함되어 있으면 접속을 차단하고 에러 응답을 보냄
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied due to special characters in the URL.");
        } else {
            System.out.println("특수문자 미발견");
            // 특수 문자가 포함되어 있지 않으면 다음 필터로 체인을 전달
            chain.doFilter(request, response);
        }
    }

    // 특수 문자 포함 여부 확인 메소드
    public boolean containsSpecialCharacter(String url) {
        System.out.println("2");
        // 특수 문자 목록
        final String specialCharacters = "!\"'()*+,-.%;<=>@[\\]^_`{|}~";

        // URL 에 특수 문자가 포함되어 있는지 확인
        for (int i = 0; i < url.length(); i++) {
            char c = url.charAt(i);
            if (specialCharacters.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter init");
        Filter.super.init(filterConfig);
    }
    @Override
    public void destroy() {
       log.info("filter destroy");
       Filter.super.destroy();
    }
}
