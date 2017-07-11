package com.github.argherna.spring.security.sample.kerberos.webview;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.kerberos.authentication.KerberosAuthenticationProvider;
import org.springframework.security.kerberos.authentication.sun.SunJaasKerberosClient;

@Configuration
@EnableWebSecurity
@Order(200)
public class WebviewSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(kerberosAuthenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/", "/open", "/index").permitAll().anyRequest()
        .authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
        .permitAll();
  }

  private AuthenticationProvider kerberosAuthenticationProvider() {
    KerberosAuthenticationProvider provider = new KerberosAuthenticationProvider();
    SunJaasKerberosClient client = new SunJaasKerberosClient();
    client.setDebug(true);
    provider.setKerberosClient(client);
    provider.setUserDetailsService(kerberosUserDetailsService());
    return provider;
  }

  private UserDetailsService kerberosUserDetailsService() {
    return new KerberosUserDetailsService();
  }
}
