package info.einverne.springboot.demo.cycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author einverne
 * @since 2019-12-10
 */
@Slf4j
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {


  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    log.info("BeanPostProcessor postProcessBeforeInitialization for:" + beanName);
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    log.info("BeanPostProcessor postProcessAfterInitialization for:gg" + beanName);
    return bean;
  }
}
