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

import java.security.Principal;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CurrentUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter methodParameter) {
    return methodParameter.getParameterAnnotation(CurrentUser.class) != null
        && methodParameter.getParameterType().equals(User.class);
  }

  @Override
  public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    if (this.supportsParameter(methodParameter)) {
      Principal principal = (Principal) webRequest.getUserPrincipal();
      return (User) ((Authentication) principal).getPrincipal();
    } else {
      return WebArgumentResolver.UNRESOLVED;
    }
  }
}