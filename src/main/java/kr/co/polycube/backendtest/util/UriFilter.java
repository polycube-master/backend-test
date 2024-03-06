package kr.co.polycube.backendtest.util;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UriFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        StringBuffer requestURL = httpRequest.getRequestURL();
        String queryString = httpRequest.getQueryString();

        String fullURL = requestURL.toString() + (queryString == null ? "" : "?" + queryString);

        if (!fullURL.matches("^[\\w\\d/?&=:.]*$")) {
            httpResponse.setContentType("application/json");
            httpResponse.setCharacterEncoding("UTF-8");
            // Controller에 대한 에러 처리를 하는 ExceptionHandler를 UriFilter에 적용하려다보니
            // Servelet 관련된 설정이 너무 복잡해져서 HttpResponse를 통해서 에러 처리 하였습니다.
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpResponse.getWriter().write("{\"reason\": \"특수 문자를 넣을 수 없습니다.\"}");
            return;
        }

        chain.doFilter(request, response);
    }
}
