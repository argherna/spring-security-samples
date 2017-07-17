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
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

  private final SampleAppKerberosApi sampleAppKerberosApi;

  @Autowired
  public ApiSecurityConfig(SampleAppKerberosApi sampleAppKerberbosApi) {
    this.sampleAppKerberosApi = sampleAppKerberbosApi;
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

  @Bean
  public KerberosServiceAuthenticationProvider kerberosServiceAuthenticationProvider() {
    KerberosServiceAuthenticationProvider provider = new KerberosServiceAuthenticationProvider();
    provider.setTicketValidator(sunJaasKerberosTicketValidator());
    provider.setUserDetailsService(kerberosUserDetailsService());
    return provider;
  }

  @Bean
  public SunJaasKerberosTicketValidator sunJaasKerberosTicketValidator() {
    SunJaasKerberosTicketValidator ticketValidator = new SunJaasKerberosTicketValidator();
    ticketValidator.setServicePrincipal(sampleAppKerberosApi.getServicePrincipal());
    ticketValidator.setKeyTabLocation(new FileSystemResource(sampleAppKerberosApi.getKeytabLocation()));
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
    return new KerberosUserDetailsServiceImpl();
  }
}
