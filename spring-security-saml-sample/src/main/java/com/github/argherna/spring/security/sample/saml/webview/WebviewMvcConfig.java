/*
 * Copyright 2016 Vincenzo De Notaris
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This file was modified from the original:
 *
 * - Code changes to fit into a different environment.
 *
 * - Root package name change from com.vdentotaris.spring.boot.security.saml to
 * com.github.argherna.spring.security.saml.sample.
 *
 */

package com.github.argherna.spring.security.sample.saml.webview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebviewMvcConfig extends WebMvcConfigurerAdapter {

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
  }

  /*
  @Bean
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.addDialect(new SpringSecurityDialect());
    return templateEngine;
  }
  */
}
