# spring-security-samples Kerberos API Sample

## Overview

This is an example API server that demonstrates how to configure spring-security-kerberos for authentication via BASIC authentication and with SPNego tokens. It will display JSON output.

---------

## References

#### Spring Boot

Spring Boot makes it easy to create Spring-powered, production-grade applications and services with absolute minimum fuss. It takes an opinionated view of the Spring platform so that new and existing users can quickly get to the bits they need.

- **Website:** [http://projects.spring.io/spring-boot/](http://projects.spring.io/spring-boot/)

#### Spring Security Kerberos Extension

Spring Kerberos Extension allows seamless inclusion of Kerberos web authentication capabilities via SPNego in Spring applications. All products supporting Kerberos (e.g. Apache Directory Service, Active Directory, etc) will be able to work with Kerberos.

- **Website:** [http://projects.spring.io/spring-security-kerberos/](http://projects.spring.io/spring-security-kerberos/)

---------

## Building, Installing and Running

You will need the following:

* JDK 1.8+
* [Maven 3.3.x](https://maven.apache.org/)

### Building and Running from Source

1. Edit `src/main/resources/application.properties` to suit your environment (see the [wiki](https://github.com/argherna/spring-security-samples/wiki) for details).
1. Run maven to build the project:

       mvn clean package

1. Unpack `target/spring-security-kerberos-api-sample-1.0.0.tar.gz` in your target environment.
1. Start the sample in your target directory.

       ./kerberos-api.sh start

### Installing and Running from Binaries

1. Unpack `spring-security-kerberos-api-sample-1.0.0.tar.gz`.
1. Create a file `config/application.properties` with your settings.
1. Start the sample in your target directory.

       ./kerberos-api.sh start

### Stopping the Sample

Run the script to stop the sample:

    ./kerberos-api.sh stop

---------

## Project description

Currently Spring Security Kerberos module doesn't provide a starter for Spring Boot. The aim of this project is to explain how to develop a **Kerberos-Authenticated web application** which uses **Spring Boot** (`1.5.3.RELEASE`) and **Spring Security Kerberos Extension** (`1.0.1.RELEASE`), by defining an annotation-based configuration (**Java Configuration**). **Thymeleaf** is also used as template engine.

- **Author:** Andy Gherna ([argherna@gmail.com](mailto:argherna@gmail.com))
- **Version:**  `1.0.0.RELEASE`
- **Date**: 2017-07-29

Thanks to *Janne Valkealahti* ([github.com/jvalkeal](https://github.com/jvalkeal)) for authoring [spring-security-kerberos](https://github.com/spring-projects/spring-security-kerberos).