package info.einverne.springboot.demo.dao;

import info.einverne.springboot.demo.annotation.AutoLog;
import info.einverne.springboot.demo.annotation.Intercepted;
import org.springframework.stereotype.Component;

/**
 * @since 2019-12-17
 */
@AutoLog
@Intercepted
@Component
public class AnnotatedClass {

  public void doSomething() {}
  public void doSomethingElse() {}

}
