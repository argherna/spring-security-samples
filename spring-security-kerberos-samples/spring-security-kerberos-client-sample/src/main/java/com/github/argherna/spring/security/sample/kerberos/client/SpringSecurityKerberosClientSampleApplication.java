package com.github.argherna.spring.security.sample.kerberos.client;

import java.io.IOException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.kerberos.client.KerberosRestTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@EnableConfigurationProperties(SampleAppKerberosClient.class)
@SpringBootApplication
public class SpringSecurityKerberosClientSampleApplication
    implements ApplicationRunner, ResponseErrorHandler {

  private static final Logger logger =
      LoggerFactory.getLogger(SpringSecurityKerberosClientSampleApplication.class);

  private final SampleAppKerberosClient sampleAppKerberosClient;

  @Autowired
  public SpringSecurityKerberosClientSampleApplication(
      SampleAppKerberosClient sampleAppKerberosClient) {
    this.sampleAppKerberosClient = sampleAppKerberosClient;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    KerberosRestTemplate restTemplate =
        new KerberosRestTemplate(sampleAppKerberosClient.getKeytabLocation(),
            sampleAppKerberosClient.getUserPrincipal(), Collections.singletonMap("debug", "true"));
    restTemplate.setErrorHandler(this);
    ResourceContent resourceContent =
        restTemplate.getForObject(sampleAppKerberosClient.getUrl(), ResourceContent.class);
    if (resourceContent != null) {
      logger.info("resourceContent = {}", resourceContent);
    }
  }

  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    return response.getStatusCode().is4xxClientError()
        || response.getStatusCode().is5xxServerError();
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    logger.warn("Failure response! Status = {}, Message = {}", response.getRawStatusCode(),
        StringUtils.hasText(response.getStatusText()) ? response.getStatusText()
            : response.getStatusCode().getReasonPhrase());
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityKerberosClientSampleApplication.class, args);
  }

  /**
   * Json object retrieved from the API.
   *
   * @author agherna
   *
   */
  public static class ResourceContent {

    private final String content;

    @JsonCreator
    public ResourceContent(@JsonProperty("content") String content) {
      this.content = content;
    }

    public String getContent() {
      return content;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((content == null) ? 0 : content.hashCode());
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
      if (!(obj instanceof ResourceContent)) {
        return false;
      }
      ResourceContent other = (ResourceContent) obj;
      if (content == null) {
        if (other.content != null) {
          return false;
        }
      } else if (!content.equals(other.content)) {
        return false;
      }
      return true;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("ResourceContent [");
      if (content != null) {
        builder.append("content=").append(content);
      }
      builder.append("]");
      return builder.toString();
    }

  }
}
