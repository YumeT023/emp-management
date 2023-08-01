package com.prog4.controller.conf;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class FilterConfig {
  @Bean
  public FilterRegistrationBean<AuthenticationFilter> sessionFilter() {
    FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new AuthenticationFilter());
    registrationBean.addUrlPatterns("/employees"); // Apply the filter to all URL patterns
    registrationBean.addUrlPatterns("/employees/*"); // Apply the filter to all URL patterns
    registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
    return registrationBean;
  }
}
