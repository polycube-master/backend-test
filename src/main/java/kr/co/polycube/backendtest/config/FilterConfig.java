package kr.co.polycube.backendtest.config;

import kr.co.polycube.backendtest.filter.SpecialCharacterFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<SpecialCharacterFilter> specialCharacterFilter() {
        FilterRegistrationBean<SpecialCharacterFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SpecialCharacterFilter());
        registrationBean.addUrlPatterns("/users/*");
        return registrationBean;
    }
}
