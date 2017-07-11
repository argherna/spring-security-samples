package com.github.argherna.spring.security.sample.kerberos.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.kerberos.authentication.KerberosAuthenticationProvider;
import org.springframework.security.kerberos.authentication.KerberosServiceAuthenticationProvider;
import org.springframework.security.kerberos.authentication.sun.SunJaasKerberosClient;
import org.springframework.security.kerberos.authentication.sun.SunJaasKerberosTicketValidator;
import org.springframework.security.kerberos.web.authentication.SpnegoAuthenticationProcessingFilter;
import org.springframework.security.kerberos.web.authentication.SpnegoEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class WebapiSecurityConfig extends WebSecurityConfigurerAdapter {

  private final SampleAppApi sampleApp;

  @Autowired
  public WebapiSecurityConfig(SampleAppApi sampleApp) {
    this.sampleApp = sampleApp;
  }

  @Bean
  public SpnegoEntryPoint spnegoEntryPoint() {
    return new SpnegoEntryPoint();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.exceptionHandling().authenticationEntryPoint(spnegoEntryPoint()).and().authorizeRequests()
        .antMatchers("/openinfo", "/openinfo/**").permitAll().anyRequest().authenticated().and()
        .httpBasic().and().logout().permitAll().and()
        .addFilterBefore(spnegoAuthenticationProcessingFilter(authenticationManagerBean()),
            BasicAuthenticationFilter.class);
  }

  @Bean
  public SpnegoAuthenticationProcessingFilter spnegoAuthenticationProcessingFilter(
      AuthenticationManager authenticationManager) {
    SpnegoAuthenticationProcessingFilter filter = new SpnegoAuthenticationProcessingFilter();
    filter.setAuthenticationManager(authenticationManager);
    return filter;
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(kerberosAuthenticationProvider())
        .authenticationProvider(kerberosServiceAuthenticationProvider());
  }

  private KerberosServiceAuthenticationProvider kerberosServiceAuthenticationProvider() {
    KerberosServiceAuthenticationProvider provider = new KerberosServiceAuthenticationProvider();
    provider.setTicketValidator(sunJaasKerberosTicketValidator());
    provider.setUserDetailsService(kerberosUserDetailsService());
    return provider;
  }

  private SunJaasKerberosTicketValidator sunJaasKerberosTicketValidator() {
    SunJaasKerberosTicketValidator ticketValidator = new SunJaasKerberosTicketValidator();
    ticketValidator.setServicePrincipal(sampleApp.getServicePrincipal());
    ticketValidator.setKeyTabLocation(new FileSystemResource(sampleApp.getKeytabLocation()));
    ticketValidator.setDebug(true);
    return ticketValidator;
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
