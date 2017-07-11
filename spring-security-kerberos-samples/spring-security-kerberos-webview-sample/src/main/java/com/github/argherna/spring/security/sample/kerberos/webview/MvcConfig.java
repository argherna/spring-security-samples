package com.github.argherna.spring.security.sample.kerberos.webview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

  @Autowired
  private CurrentUserHandlerMethodArgumentResolver currentUserHandlerMethodArgumentResolver;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(currentUserHandlerMethodArgumentResolver);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("index");
    registry.addViewController("/error").setViewName("error");
    registry.addViewController("/open").setViewName("open");
    registry.addViewController("/secure").setViewName("secure");
    registry.addViewController("/login").setViewName("login");
  }
}
