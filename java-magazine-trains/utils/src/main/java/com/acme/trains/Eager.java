package com.acme.trains;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * <p>Specifies that a CDI bean should be initialized at applications start. Similar to how @Startup works for EJB.</p>
 *
 * @author Tilen Faganel
 * @since 2.0.0
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Eager {
}