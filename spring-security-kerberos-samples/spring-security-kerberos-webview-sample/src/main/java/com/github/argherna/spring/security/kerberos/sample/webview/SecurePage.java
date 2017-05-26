package com.github.argherna.spring.security.kerberos.sample.webview;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/secureinfo")
public class SecurePage {

  @GetMapping
  public String getView(@CurrentUser UserDetails user, Model model) {
    model.addAttribute("username", user.getUsername());
    return "secure";
  }
}
