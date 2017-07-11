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

@EnableConfigurationProperties(SampleApp.class)
@SpringBootApplication
public class SpringSecurityKerberosClientSampleApplication
    implements ApplicationRunner, ResponseErrorHandler {

  private static final Logger logger =
      LoggerFactory.getLogger(SpringSecurityKerberosClientSampleApplication.class);

  private final SampleApp sampleApp;

  @Autowired
  public SpringSecurityKerberosClientSampleApplication(SampleApp sampleApp) {
    this.sampleApp = sampleApp;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    KerberosRestTemplate restTemplate = new KerberosRestTemplate(sampleApp.getKeytabLocation(),
        sampleApp.getUserPrincipal(), Collections.singletonMap("debug", "true"));
    restTemplate.setErrorHandler(this);
    ResourceContent resourceContent = restTemplate.getForObject(sampleApp.getUrl(), ResourceContent.class);
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
    public String toString() {
      return getClass().getSimpleName() + " [content=" + content + "]";
    }
  }
}
