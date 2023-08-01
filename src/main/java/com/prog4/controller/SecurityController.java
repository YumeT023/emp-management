package com.prog4.controller;

import com.prog4.controller.model.LoginPayload;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class SecurityController {
  public static final String AUTH_KEY = "token";

  @GetMapping("/login")
  public String loginPage(Model model, HttpSession session) {
    model.addAttribute("payload", new LoginPayload());
    return "login";
  }

  @PostMapping("/login")
  public String doLogin(@ModelAttribute LoginPayload payload, HttpSession session) {
    LocalDateTime expiration = (LocalDateTime) session.getAttribute(AUTH_KEY);
    if (expiration != null && LocalDateTime.now().isBefore(expiration)) {
      return "redirect:/employees";
    }
    LocalDateTime newExpiration = LocalDateTime.now().plusMinutes(30);
    session.setAttribute(AUTH_KEY, newExpiration);
    return "redirect:/employees";
  }
}
