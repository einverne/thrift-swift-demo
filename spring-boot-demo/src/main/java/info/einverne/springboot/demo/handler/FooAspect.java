package info.einverne.springboot.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
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
public class FooAspect {

  /**
   * matching specific method
   */
  @Pointcut("execution(public String info.einverne.springboot.demo.dao.FooDao.get(Long))")
  public void logSpecificMethod() {}

  /**
   * matching all methods
   */
  @Pointcut("execution(* info.einverne.springboot.demo.dao.FooDao..*(..))")
  public void logAllMethods() {}

  /**
   * matching within Class
   */
  @Pointcut("within(info.einverne.springboot.demo.dao.FooDao)")
  public void logWithClass() {}

  /**
   * matching within package
   */
  @Pointcut("within(info.einverne.springboot.demo.dao..*)")
  public void logWithPackage() {}

  @Pointcut("target(info.einverne.springboot.demo.dao.BaseDao)")
  public void logBaseDao() {}

  @Pointcut("this(info.einverne.springboot.demo.dao.FooDao)")
  public void logThis() {}

//  @Pointcut("execution(* *..get(Long))")
//  public void matchArgsLong() {}
//
//  @Pointcut("execution(* *..get(Long,..))")
//  public void matchArgsL() {}
//
//  @Pointcut("@target(org.springframework.stereotype.Controller)")
//  public void logRepository() {}

//  @Before("logRepository()")
//  public void logDemo(JoinPoint jp) {
//    log.info("log demo repository " + jp.getSignature().getClass().getSimpleName());
//  }

  @Before("execution(* info.einverne.springboot.demo.dao.FooDao..*(..))")         //point-cut expression
  public void logBefore(JoinPoint joinPoint) {
    log.info("FooAspect.logBefore() : " + joinPoint.getSignature().getName());
  }
}
