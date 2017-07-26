# spring-security-samples SAML2 Sample

## Overview

This is an example web application that demonstrates how to configure and use `spring-security-saml` for authentication. There is a page that requires no authentication (`/openinfo`) and a page that requires authentication (`/secureinfo`). The authenticated page will display a user's SAML2 attributes along with custom role information.

---------


## References

#### Spring Boot

Spring Boot makes it easy to create Spring-powered, production-grade applications and services with absolute minimum fuss. It takes an opinionated view of the Spring platform so that new and existing users can quickly get to the bits they need.

- **Website:** [http://projects.spring.io/spring-boot/](http://projects.spring.io/spring-boot/)

#### Spring Security SAML Extension

Spring SAML Extension allows seamless inclusion of SAML 2.0 Service Provider capabilities in Spring applications. All products supporting SAML 2.0 in Identity Provider mode (e.g. ADFS 2.0, Shibboleth, OpenAM/OpenSSO, Ping Federate, Okta) can be used to connect with Spring SAML Extension.

- **Website:** [http://projects.spring.io/spring-security-saml/](http://projects.spring.io/spring-security-saml/)

---------

## Building, Installing and Running

You will need the following:

* JDK 1.8+
* [Maven 3.3.x](https://maven.apache.org/)

### Building and Running from Source

1. Edit `src/main/resources/application.properties` to suit your environment (see the [wiki](https://github.com/argherna/spring-security-samples/wiki) for details).
1. Run maven to build the project:

       mvn clean package

1. Unpack `target/spring-security-saml-webview-sample-1.0.0.tar.gz` in your target environment.
1. Start the sample in your target directory.

       ./saml-webview.sh start

### Installing and Running from Binaries

1. Unpack `spring-security-saml-webview-sample-1.0.0.tar.gz`.
1. Create a file `config/application.properties` with your settings.
1. Start the sample in your target directory.

       ./saml-webview.sh start

### Stopping the Sample

Run the script to stop the sample:

    ./saml-webview.sh stop

---------

## Project description

Currently Spring Security SAML module doesn't provide a starter for Spring Boot. Moreover, its configuration is XML-based as of this writing. The aim of this project is to explain how to develop a **Service Provider (SP)** which uses **Spring Boot** (`1.5.3.RELEASE`) and **Spring Security SAML Extension** (`1.0.2.RELEASE`), by defining an annotation-based configuration (**Java Configuration**). **Thymeleaf** is also used as template engine.

**TestShib** ([testshib.org](http://www.testshib.org/)) is used as public Identity Provider for testing purposes.

- **Author:** Andy Gherna ([argherna@gmail.com](mailto:argherna@gmail.com))
- **Author:** Vincenzo De Notaris ([dev@vdenotaris.com](mailto:dev@vdenotaris.com))
- **Version:**  `1.0.0.RELEASE`
- **Date**: 2017-07-29

Thanks to *Vladimír Schäfer* ([github.com/vschafer](https://github.com/vschafer)) for authoring [spring-security-saml](https://github.com/spring-projects/spring-security-saml). Thanks to *Vincenzo De Notaris* ([github.com/vdenotaris](https://github.com/vdenotaris)) for his work on establishing the project this work was forked and adapted from. Source files contain attribution that conforms to the specifications in the Apache 2.0 License.
