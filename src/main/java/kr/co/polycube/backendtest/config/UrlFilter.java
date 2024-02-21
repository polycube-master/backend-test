package kr.co.polycube.backendtest.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class UrlFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String url = httpRequest.getRequestURL().toString();

        boolean tf = containsSpecialCharacters(url);
        log.info("forbidden character : {}", tf);

        if (containsSpecialCharacters(url)) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "허용되지 않은 특수문자를 포함한 URL입니다.");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean containsSpecialCharacters(String url) {

        String regex = "[^a-zA-Z0-9?/:&=]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        return matcher.find();
    }
}
