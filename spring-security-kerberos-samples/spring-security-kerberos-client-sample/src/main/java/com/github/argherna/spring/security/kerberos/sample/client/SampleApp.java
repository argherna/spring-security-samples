package com.github.argherna.spring.security.kerberos.sample.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("sampleapp")
public class SampleApp {

  public String url;

  public String keytabLocation;

  public String userPrincipal;

  public SampleApp() {}

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getKeytabLocation() {
    return keytabLocation;
  }

  public void setKeytabLocation(String keytabLocation) {
    this.keytabLocation = keytabLocation;
  }

  public String getUserPrincipal() {
    return userPrincipal;
  }

  public void setUserPrincipal(String userPrincipal) {
    this.userPrincipal = userPrincipal;
  }

  @Override
  public String toString() {
    return "SampleApp [url=" + url + ", keytabLocation=" + keytabLocation + ", userPrincipal="
        + userPrincipal + "]";
  }

}
