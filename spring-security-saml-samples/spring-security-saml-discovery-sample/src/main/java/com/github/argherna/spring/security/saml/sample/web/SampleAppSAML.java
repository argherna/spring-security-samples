package com.github.argherna.spring.security.saml.sample.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sampleapp-saml")
public class SampleAppSAML {

  private String keyStoreFile;

  private String keyStorePassword;

  private String keyStoreDefaultKey;

  private String keyPassword;

  private int defaultPort;

  private String idpMetadataUrl;

  private String entityID;

  private String idpDiscoveryUrl;

  private String idpDiscoveryResponseUrl;

  public String getKeyStoreFile() {
    return keyStoreFile;
  }

  public void setKeyStoreFile(String keyStoreFile) {
    this.keyStoreFile = keyStoreFile;
  }

  public String getKeyStorePassword() {
    return keyStorePassword;
  }

  public void setKeyStorePassword(String keyStorePassword) {
    this.keyStorePassword = keyStorePassword;
  }

  public String getKeyStoreDefaultKey() {
    return keyStoreDefaultKey;
  }

  public void setKeyStoreDefaultKey(String keyStoreDefaultKey) {
    this.keyStoreDefaultKey = keyStoreDefaultKey;
  }

  public String getKeyPassword() {
    return keyPassword;
  }

  public void setKeyPassword(String keyPassword) {
    this.keyPassword = keyPassword;
  }

  public int getDefaultPort() {
    return defaultPort;
  }

  public void setDefaultPort(int defaultPort) {
    this.defaultPort = defaultPort;
  }

  public String getIdpMetadataUrl() {
    return idpMetadataUrl;
  }

  public void setIdpMetadataUrl(String idpMetadataUrl) {
    this.idpMetadataUrl = idpMetadataUrl;
  }

  public String getEntityID() {
    return entityID;
  }

  public void setEntityID(String entityID) {
    this.entityID = entityID;
  }

  public String getIdpDiscoveryUrl() {
    return idpDiscoveryUrl;
  }

  public void setIdpDiscoveryUrl(String idpDiscoveryUrl) {
    this.idpDiscoveryUrl = idpDiscoveryUrl;
  }

  public String getIdpDiscoveryResponseUrl() {
    return idpDiscoveryResponseUrl;
  }

  public void setIdpDiscoveryResponseUrl(String idpDiscoveryResponseUrl) {
    this.idpDiscoveryResponseUrl = idpDiscoveryResponseUrl;
  }

  @Override
  public String toString() {
    return "SampleApp [keyStoreFile=" + keyStoreFile
        + ", keyStorePassword=********, keyStoreDefaultKey=" + keyStoreDefaultKey + ", defaultPort="
        + defaultPort + ", idpMetadataUrl=" + idpMetadataUrl + ", entityID=" + entityID
        + ", idpDiscoveryUrl=" + idpDiscoveryUrl + ", idpDiscoveryResponseUrl="
        + idpDiscoveryResponseUrl + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + defaultPort;
    result = prime * result + ((entityID == null) ? 0 : entityID.hashCode());
    result = prime * result + ((idpMetadataUrl == null) ? 0 : idpMetadataUrl.hashCode());
    result = prime * result + ((keyStoreDefaultKey == null) ? 0 : keyStoreDefaultKey.hashCode());
    result = prime * result + ((keyStoreFile == null) ? 0 : keyStoreFile.hashCode());
    result = prime * result + ((keyStorePassword == null) ? 0 : keyStorePassword.hashCode());
    result = prime * result + ((idpDiscoveryUrl == null) ? 0 : idpDiscoveryUrl.hashCode());
    result = prime * result
        + ((idpDiscoveryResponseUrl == null) ? 0 : idpDiscoveryResponseUrl.hashCode());
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
    if (getClass() != obj.getClass()) {
      return false;
    }
    SampleAppSAML other = (SampleAppSAML) obj;
    if (defaultPort != other.defaultPort) {
      return false;
    }
    if (entityID == null) {
      if (other.entityID != null) {
        return false;
      }
    } else if (!entityID.equals(other.entityID)) {
      return false;
    }
    if (idpMetadataUrl == null) {
      if (other.idpMetadataUrl != null) {
        return false;
      }
    } else if (!idpMetadataUrl.equals(other.idpMetadataUrl)) {
      return false;
    }
    if (keyStoreDefaultKey == null) {
      if (other.keyStoreDefaultKey != null) {
        return false;
      }
    } else if (!keyStoreDefaultKey.equals(other.keyStoreDefaultKey)) {
      return false;
    }
    if (keyStoreFile == null) {
      if (other.keyStoreFile != null) {
        return false;
      }
    } else if (!keyStoreFile.equals(other.keyStoreFile)) {
      return false;
    }
    if (keyStorePassword == null) {
      if (other.keyStorePassword != null) {
        return false;
      }
    } else if (!keyStorePassword.equals(other.keyStorePassword)) {
      return false;
    }
    if (idpDiscoveryUrl == null) {
      if (other.idpDiscoveryUrl != null) {
        return false;
      }
    } else if (!idpDiscoveryUrl.equals(other.idpDiscoveryUrl)) {
      return false;
    }
    if (idpDiscoveryResponseUrl == null) {
      if (other.idpDiscoveryResponseUrl != null) {
        return false;
      }
    } else if (!idpDiscoveryResponseUrl.equals(other.idpDiscoveryResponseUrl)) {
      return false;
    }
    return true;
  }

}
