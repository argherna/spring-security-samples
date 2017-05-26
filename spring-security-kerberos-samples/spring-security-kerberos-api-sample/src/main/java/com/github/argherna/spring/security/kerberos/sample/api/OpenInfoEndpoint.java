package com.github.argherna.spring.security.kerberos.sample.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/openinfo", produces = {"application/json"})
public class OpenInfoEndpoint {

  @GetMapping
  public HttpEntity<ResourceContent> getResource() {
    ResourceContent info = new ResourceContent("some open information");
    return new ResponseEntity<>(info, HttpStatus.OK);
  }
}
