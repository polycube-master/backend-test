package kr.co.polycube.backendtest.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
@Component
@Order(1)
public class SpecialCharacterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        // 특수 문자 검사
        if (containsSpecialCharacter(requestURI)) {
            // 특수 문자가 포함되어 있으면 접속을 차단하고 에러 응답을 보냄
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied due to special characters in the URL.");
        } else {
            // 특수 문자가 포함되어 있지 않으면 다음 필터로 체인을 전달
            chain.doFilter(request, response);
        }
    }

    // 특수 문자 포함 여부 확인 메소드
    private boolean containsSpecialCharacter(String url) {
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
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void destroy() {}
}
