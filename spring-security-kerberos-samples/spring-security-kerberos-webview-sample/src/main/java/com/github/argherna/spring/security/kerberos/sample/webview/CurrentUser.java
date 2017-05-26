/**
 *
 */
package com.github.argherna.spring.security.kerberos.sample.webview;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
@AuthenticationPrincipal
/**
 * @author agherna
 *
 */
public @interface CurrentUser {

}
