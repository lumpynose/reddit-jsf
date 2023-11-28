package com.objecteffects.reddit.jsf.logging;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

/**
 *
 */
@ApplicationScoped
public class LoggerProducer implements Serializable {
    private static final long serialVersionUID = 6896451058527904256L;

    @Produces
    Logger produceLogger(final InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint
                .getMember()
                .getDeclaringClass()
                .getSimpleName());
    }

//  @Produces
//  public Logger produceLogger(final InjectionPoint injectionPoint) {
//      final Class<?> loggerName = extractLoggerName(injectionPoint);
//
//      return LoggerFactory.getLogger(loggerName);
//  }
//
//  private Class<?> extractLoggerName(final InjectionPoint injectionPoint) {
//      if (injectionPoint.getBean() == null) {
//          return injectionPoint.getMember().getDeclaringClass();
//      }
//
//      return injectionPoint.getBean().getBeanClass();
//  }
}
