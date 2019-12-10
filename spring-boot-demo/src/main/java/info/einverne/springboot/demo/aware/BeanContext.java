package info.einverne.springboot.demo.aware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author einverne
 * @since 2019-12-10
 */
@Slf4j
@Component
public class BeanContext implements ApplicationContextAware {


  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    log.info("ApplicationContextAware setApplicationContext()");
  }
}
