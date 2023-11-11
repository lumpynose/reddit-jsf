package org.objecteffects.reddit.jsf.logging;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

//@Model
//@Singleton
//public class LoggerProducer {
//    @Produces
//    public Logger produceLogger(final InjectionPoint injectionPoint) {
//        return LoggerFactory
//                .getLogger(injectionPoint.getMember().getDeclaringClass());
//    }
//}

@ApplicationScoped
public class LoggerProducer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Produces
    public Logger produceLogger(final InjectionPoint injectionPoint) {
        final Class<?> loggerName = extractLoggerName(injectionPoint);

        return LoggerFactory.getLogger(loggerName);
    }

    private Class<?> extractLoggerName(final InjectionPoint injectionPoint) {
        if (injectionPoint.getBean() == null) {
            return injectionPoint.getMember().getDeclaringClass();
        }

        return injectionPoint.getBean().getBeanClass();
    }
}
