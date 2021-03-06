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

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.metadata.MetadataManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/saml/idpSelection")
public class IdpSelectionController {

  private static final Logger logger = LoggerFactory.getLogger(IdpSelectionController.class);

  @Autowired
  private MetadataManager metadata;

  @GetMapping
  public String getView(HttpServletRequest request, Model model) {
    if (!(SecurityContextHolder.getContext()
        .getAuthentication() instanceof AnonymousAuthenticationToken)) {
      logger.warn("The current user is already logged in.");
      return "redirect:/secure";
    } else {
      if (isForwarded(request)) {
        Set<String> idps = metadata.getIDPEntityNames();
        logger.info("Configured Identity Provider for SSO: {}", idps);
        model.addAttribute("idps", idps);
        return "saml/idpselection";
      } else {
        logger.warn("Direct accesses to '/idpSelection' route are not allowed");
        return "redirect:/";
      }
    }
  }

  /*
   * Checks if an HTTP request is forwarded from servlet.
   */
  private boolean isForwarded(HttpServletRequest request) {
    return (request.getAttribute("javax.servlet.forward.request_uri") == null);
  }

}
