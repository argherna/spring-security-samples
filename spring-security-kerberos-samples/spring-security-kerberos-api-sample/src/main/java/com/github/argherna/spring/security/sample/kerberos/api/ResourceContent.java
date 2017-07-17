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
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((content == null) ? 0 : content.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
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
