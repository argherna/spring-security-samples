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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/secureinfo")
public class SecureInfoController {

  private static final Logger logger = LoggerFactory.getLogger(SecureInfoController.class);

  @GetMapping
  public String getView(@CurrentUser SAMLUser user, Model model) {
    model.addAttribute("username", user.getUsername());
    MultiValueMap<String, String> allUserAttributes = user.getAllAttributes();
    if (allUserAttributes.isEmpty()) {
      logger.warn("No user attributes are set!");
    } else {
      logger.debug("user attributes = {}", allUserAttributes);
    }
    model.addAttribute("authoritynames", user.getAuthorityNames());
    model.addAttribute("attributes", user.getAllAttributesFlat());
    return "secure";
  }

}
