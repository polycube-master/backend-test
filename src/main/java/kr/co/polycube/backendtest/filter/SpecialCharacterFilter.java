package kr.co.polycube.backendtest.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

public class SpecialCharacterFilter implements Filter {

    private static final Pattern ALLOWED_CHARACTERS_PATTERN = Pattern.compile("[a-zA-Z0-9/?=&]");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String queryString = request.getParameter("name");

        if (queryString != null && !ALLOWED_CHARACTERS_PATTERN.matcher(queryString).matches()) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setContentType("text/plain");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.getWriter().write("쿼리 문자열에는 특수문자는 허용되지 않습니다.");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
