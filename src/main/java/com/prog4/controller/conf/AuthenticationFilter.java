package com.prog4.controller.conf;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationFilter implements Filter {

  private static final String AUTH_KEY = "token";

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpSession session = httpRequest.getSession(false);

    log.info("filtering here {} {}", ((HttpServletRequest) request).getMethod(), ((HttpServletRequest) request).getServletPath());

    String path = ((HttpServletRequest) request).getServletPath();

    if (!path.startsWith("/employees")) {
      chain.doFilter(request, response);
      return;
    }


    if (session != null) {
      LocalDateTime expiration = (LocalDateTime) session.getAttribute(AUTH_KEY);
      if (expiration != null && LocalDateTime.now().isBefore(expiration)) {
        chain.doFilter(request, response);
        return;
      }
    }

    // User is not authenticated or session has expired, redirect to login page
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    httpResponse.sendRedirect("/login");
  }
}
