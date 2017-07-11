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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class SAMLUserDetailsServiceImpl implements SAMLUserDetailsService {

  // Logger
  private static final Logger LOG = LoggerFactory.getLogger(SAMLUserDetailsServiceImpl.class);

  private final String rolePrefix;

  @Autowired
  public SAMLUserDetailsServiceImpl(@Value("${sampleapp-saml.role-prefix}") String rolePrefix) {
    this.rolePrefix = rolePrefix;
  }

  @PostConstruct
  public void logSetup() {
    LOG.debug("rolePrefix = {}", this.rolePrefix);
  }

  @Override
  public Object loadUserBySAML(SAMLCredential credential) throws UsernameNotFoundException {

    // The method is supposed to identify local account of user referenced by
    // data in the SAML assertion and return UserDetails object describing the user.

    // Try to get the userID from the uid
    String userID = credential.getAttributeAsString("urn:oid:0.9.2342.19200300.100.1.1");
    if (userID == null || userID.isEmpty()) {
      LOG.info("Requesting userID with friendly name");
      userID = credential.getAttributeAsString("uin");
    }

    LOG.info("{} is logged in", userID);

    // Make a list of authorities based on the 'isMemberOf' attribute.
    String[] roles = credential.getAttributeAsStringArray("urn:oid:1.3.6.1.4.1.5923.1.5.1.1");
    if (roles == null || roles.length == 0) {
      LOG.warn("isMemberOf attibute not set by SAML2!");
    }
    List<GrantedAuthority> authorities = (roles == null || roles.length == 0) ? new ArrayList<>()
        : Arrays.asList(roles).stream().filter(role -> {
          return role.startsWith(rolePrefix);
        }).map(role -> {
          if (role.endsWith("manager")) {
            return new SimpleGrantedAuthority("ROLE_MANAGER");
          } else {
            return new SimpleGrantedAuthority("ROLE_USER");
          }
        }).collect(Collectors.toList());

    MultiValueMap<String, String> userAttributes = new LinkedMultiValueMap<>();
    credential.getAttributes().stream().filter(attr -> {
      return credential.getAttributeAsStringArray(attr.getName()) != null
          && credential.getAttributeAsStringArray(attr.getName()).length > 0;
    }).forEach(attr -> {
      userAttributes.put(attr.getFriendlyName(),
          Arrays.asList(credential.getAttributeAsStringArray(attr.getName())));
    });

    // In a real scenario, this implementation has to locate user in a arbitrary
    // dataStore based on information present in the SAMLCredential and
    // returns such a date in a form of application specific UserDetails object.
    return new SAMLUser(userID, "********", true, true, true, true, authorities, userAttributes);
  }

}
