package com.github.argherna.spring.security.sample.kerberos.api;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data transfer object.
 *
 * @author agherna
 *
 */
public class ResourceContent extends ResourceSupport {

  private final String content;

  @JsonCreator
  public ResourceContent(@JsonProperty("content") String content) {
    this.content = content;
  }

  /**
   * Returns the content string.
   */
  public String getContent() {
    return content;
  }

  @Override
  public String toString() {
    return "ResourceContent [content=" + content + "]";
  }
}
