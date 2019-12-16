package info.einverne.springboot.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @since 2019-12-18
 */
@Slf4j
@Aspect
@Component
public class ArgsAspect {

  @Pointcut("execution(* *.*(info.einverne.springboot.demo.model.ArgsParam))")
  public void matchArgsParam() {
  }

  /**
   * Match all method with parameter ArgsParam
   *
   * @param jp jp
   */
  @Before("matchArgsParam()")
  public void log(JoinPoint jp) {
    log.info("ArgsAspect {}", jp);
  }

}
