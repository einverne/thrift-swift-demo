package info.einverne.springboot.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @since 2019-12-12
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect {

  @Before("execution(* info.einverne.springboot.demo.controller..*(..))")         //point-cut expression
  public void logBefore(JoinPoint joinPoint) {
    log.info("LoggingAspect.logBefore() : " + joinPoint.getSignature().getName());
  }

  @After("execution(* info.einverne.springboot.demo.controller..*(..))")
  public void logAfter(JoinPoint joinPoint) {
    log.info("LoggingAspect.logAfter() : " + joinPoint.getSignature().getName());
  }

  @Around("execution(* info.einverne.springboot.demo.controller..*(..))")
  public Object logAround(ProceedingJoinPoint joinPoint) {
    log.info("LoggingAspect.logAround() before: " + joinPoint.getSignature().getName());
    Object proceed = null;
    try {
      proceed = joinPoint.proceed();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    log.info("LoggingAspect.logAround() after: " + joinPoint.getSignature().getName());
    return proceed;
  }

  @Pointcut("@annotation(info.einverne.springboot.demo.annotation.AutoLog)")
  public void annotatedMethod() {}
  @Pointcut("@within(info.einverne.springboot.demo.annotation.AutoLog)")
  public void annotatedClass() {}

  @Before("annotatedMethod() || annotatedClass()")
  public void logAnnotation(JoinPoint joinPoint) {
    log.info("logAnnotation " + joinPoint.getSignature().getName());
  }


  @Before("execution(public * info.einverne.springboot.demo.controller..*(..))")
  public void logAllPublicMethod() {
    log.info("匹配所有的Public方法");
  }

  @Before("execution(String||void info.einverne.springboot.demo.controller..*(..))")
  public void logReturnType() {
    log.info("匹配所有的Return Type 为 String 或者 void 的方法");
  }
}
