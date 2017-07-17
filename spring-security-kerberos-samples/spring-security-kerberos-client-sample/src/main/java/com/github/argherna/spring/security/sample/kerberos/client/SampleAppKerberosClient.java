package com.github.argherna.spring.security.sample.kerberos.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("sampleapp-kerberos-client")
public class SampleAppKerberosClient {

  public String url;

  public String keytabLocation;

  public String userPrincipal;

  public SampleAppKerberosClient() {}

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
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((keytabLocation == null) ? 0 : keytabLocation.hashCode());
    result = prime * result + ((url == null) ? 0 : url.hashCode());
    result = prime * result + ((userPrincipal == null) ? 0 : userPrincipal.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof SampleAppKerberosClient)) {
      return false;
    }
    SampleAppKerberosClient other = (SampleAppKerberosClient) obj;
    if (keytabLocation == null) {
      if (other.keytabLocation != null) {
        return false;
      }
    } else if (!keytabLocation.equals(other.keytabLocation)) {
      return false;
    }
    if (url == null) {
      if (other.url != null) {
        return false;
      }
    } else if (!url.equals(other.url)) {
      return false;
    }
    if (userPrincipal == null) {
      if (other.userPrincipal != null) {
        return false;
      }
    } else if (!userPrincipal.equals(other.userPrincipal)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("SampleAppKerberosClient [");
    if (url != null) {
      builder.append("url=").append(url).append(", ");
    }
    if (keytabLocation != null) {
      builder.append("keytabLocation=").append(keytabLocation).append(", ");
    }
    if (userPrincipal != null) {
      builder.append("userPrincipal=").append(userPrincipal);
    }
    builder.append("]");
    return builder.toString();
  }
}
