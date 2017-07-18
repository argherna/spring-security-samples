package com.github.argherna.spring.security.sample.kerberos.webview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class KerberosUserDetailsServiceImpl implements UserDetailsService {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(KerberosUserDetailsServiceImpl.class);

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    LOGGER.info("{} is logged in from kerberos", username);
    return new User(username, "********", true, true, true, true,
        AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ACTUATOR"));
  }

}
