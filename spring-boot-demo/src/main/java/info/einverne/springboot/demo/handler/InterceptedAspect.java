package info.einverne.springboot.demo.handler;

import info.einverne.springboot.demo.annotation.Intercepted;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @since 2019-12-17
 */
@Slf4j
@Aspect
@Component
public class InterceptedAspect {

  /**
   * 匹配所有使用了 Intercepted 注解的方法
   */
  @Pointcut("@annotation(info.einverne.springboot.demo.annotation.Intercepted)")
  public void annotatedMethod() {}

  /**
   * 匹配所有使用了 Intercepted注解的类的所有方法
   */
  @Pointcut("@within(info.einverne.springboot.demo.annotation.Intercepted)")
  public void annotatedClass() {}

  @Before("annotatedMethod() || annotatedClass()")
  public void logBefore(JoinPoint jp) {
    Intercepted classIntercepted = jp.getTarget().getClass().getAnnotation(Intercepted.class);
    if (classIntercepted != null && classIntercepted.logBefore()) {
      log.info(jp.toLongString());
      return;
    }
    MethodSignature ms = (MethodSignature) jp.getSignature();
    Intercepted intercepted = ms.getMethod().getAnnotation(Intercepted.class);
    if (intercepted.logBefore()) {
      log.info(jp.toLongString());
    }
  }

  @AfterReturning("annotatedMethod() || annotatedClass()")
  public void logAfterReturning(JoinPoint jp) {
    Intercepted classIntercepted = jp.getTarget().getClass().getAnnotation(Intercepted.class);
    if (classIntercepted != null && classIntercepted.logAfter()) {
      log.info(jp.toLongString());
      return;
    }
    MethodSignature ms = (MethodSignature) jp.getSignature();
    Intercepted intercepted = ms.getMethod().getAnnotation(Intercepted.class);
    if (intercepted.logAfter()) {
      log.info(jp.toLongString());
    }
  }

}
