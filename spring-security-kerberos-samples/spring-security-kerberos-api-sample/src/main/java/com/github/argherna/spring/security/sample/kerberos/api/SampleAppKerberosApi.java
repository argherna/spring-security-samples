package com.github.argherna.spring.security.sample.kerberos.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("sampleapp-kerberos-api")
public class SampleAppKerberosApi {

  private String servicePrincipal;

  private String keytabLocation;

  /**
   * Return the service principal
   */
  public String getServicePrincipal() {
    return servicePrincipal;
  }

  /**
   * Set the service principal
   *
   * @param servicePrincipal the service principal name
   */
  public void setServicePrincipal(String servicePrincipal) {
    this.servicePrincipal = servicePrincipal;
  }

  /**
   * Return the keytab filename
   */
  public String getKeytabLocation() {
    return keytabLocation;
  }

  /**
   * Set the keytab filename
   *
   * @param keytabLocation path to the keytab file
   */
  public void setKeytabLocation(String keytabLocation) {
    this.keytabLocation = keytabLocation;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("SampleAppApi [");
    if (servicePrincipal != null) {
      builder.append("servicePrincipal=").append(servicePrincipal).append(", ");
    }
    if (keytabLocation != null) {
      builder.append("keytabLocation=").append(keytabLocation);
    }
    builder.append("]");
    return builder.toString();
  }

}
