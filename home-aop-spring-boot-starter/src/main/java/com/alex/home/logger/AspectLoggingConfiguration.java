package com.alex.home.logger;

import com.alex.home.logger.annotation.LogMasked;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.StopWatch;

/**
 * Configuration covers all aspects of logging.
 *
 * @author Oleksandr Ivanov
 */
@Aspect
@EnableAspectJAutoProxy
@Configuration
public class AspectLoggingConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(AspectLoggingConfiguration.class);

    @Pointcut("within(com.alex.home..*)")
    public void allMethodsInBasePointcut() {
    }

    @Pointcut("execution(public * *(..))")
    private void anyPublicOperation() {
    }

    @Pointcut("@annotation(com.alex.home.logger.annotation.LogTime)")
    private void logTimeForAnnotatedMethod() {
    }

    @Pointcut("@annotation(logMasked)")
    private void logBOWithMaskForAnnotatedMethod(LogMasked logMasked) {
    }

    @Before("allMethodsInBasePointcut() " +
            "&& !logTimeForAnnotatedMethod() " +
            "&& !@annotation(com.alex.home.logger.annotation.LogMasked)")
    public void loggingAdvice(JoinPoint joinPoint) {
        LOG.info("Default logging: in {}", joinPoint.toShortString());
    }

    @Around("logTimeForAnnotatedMethod()")
    public Object timeLoggingForMethods(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch(pjp.getTarget().getClass().getName());
        stopWatch.start();
        Object returnValue = pjp.proceed();
        stopWatch.stop();
        LOG.info("Result is {}. Total time of {} is {} ms", returnValue, pjp.toShortString(), stopWatch.getTotalTimeMillis());
        return returnValue;
    }

    @Before(value = "logBOWithMaskForAnnotatedMethod(masked)", argNames = "masked")
    public void logBusinessObjectsWithMask(JoinPoint joinPoint, LogMasked masked) {
        LOG.info("Should we hide it? - {}. Here the mask: {}, ", masked.hide(), masked.mask());
    }

    /*
    Mutate result
     */
    @Around("logTimeForAnnotatedMethod() && args(length)")
    public Object mutateResultForMethods(ProceedingJoinPoint pjp, Long length) throws Throwable {
        LOG.info("Argument is: {}", length);
        Object result = pjp.proceed(new Object[]{100L});
        LOG.info("New answer: {}", result);
        return result;
    }


}
