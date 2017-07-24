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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class SAMLUser extends User {

  /**
   *
   */
  private static final long serialVersionUID = -3329705474105530845L;

  private final MultiValueMap<String, String> userAttributes;

  public SAMLUser(String username, String password, boolean enabled, boolean accountNonExpired,
      boolean credentialsNonExpired, boolean accountNonLocked,
      Collection<? extends GrantedAuthority> authorities,
      MultiValueMap<String, String> userAttributes) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
        authorities);
    this.userAttributes = new LinkedMultiValueMap<>();
    this.userAttributes.putAll(userAttributes);
  }

  public SAMLUser(String userID, String password, boolean enabled, boolean accountNonExpired,
      boolean credentialsNonExpired, boolean accountNonLocked, List<GrantedAuthority> authorities,
      MultiValueMap<String, String> userAttributes) {
    super(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
        authorities);
    this.userAttributes = new LinkedMultiValueMap<>();
    this.userAttributes.putAll(userAttributes);
  }

  public String getSingleValuedUserAttribute(String attributeName) {
    return userAttributes.getFirst(attributeName);
  }

  public List<String> getUserAttribute(String attributeName) {
    return userAttributes.get(attributeName);
  }

  public MultiValueMap<String, String> getAllAttributes() {
    MultiValueMap<String, String> userAttributesCopy = new LinkedMultiValueMap<>();
    userAttributesCopy.putAll(userAttributes);
    return userAttributesCopy;
  }

  public Map<String, String> getAllAttributesFlat() {
    Map<String, String> flatAttributes = new HashMap<>();
    for (String userAttribute : userAttributes.keySet()) {
      List<String> userAttributeValue = userAttributes.get(userAttribute);
      if (userAttributeValue != null & !userAttributeValue.isEmpty()) {
        String value = userAttributeValue.size() > 1
            ? userAttributeValue.stream().collect(Collectors.joining(", "))
            : userAttributeValue.get(0);
        flatAttributes.put(userAttribute, value);
      }
    }
    return flatAttributes;
  }

  public List<String> getAuthorityNames() {
    Collection<GrantedAuthority> authorities = getAuthorities();
    List<String> authoritynames;
    if (authorities != null && authorities.size() > 0) {
      authoritynames = authorities.stream().map(authority -> {
        return authority.getAuthority();
      }).collect(Collectors.toList());
    } else {
      authoritynames = new ArrayList<>();
    }
    return authoritynames;
  }
}
