package info.einverne.springboot.demo.dao;

import info.einverne.springboot.demo.annotation.Intercepted;
import org.springframework.stereotype.Component;

/**
 * @since 2019-12-17
 */
@Intercepted
@Component
public class AnnotatedMix {

  @Intercepted
  public void doSomething() {}
  public void doSomethingElse() {}

}
