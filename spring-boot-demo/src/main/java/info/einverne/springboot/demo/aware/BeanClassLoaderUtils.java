package info.einverne.springboot.demo.aware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.stereotype.Component;

/**
 * @author einverne
 * @since 2019-12-10
 */
@Slf4j
@Component
public class BeanClassLoaderUtils implements BeanClassLoaderAware {


  @Override
  public void setBeanClassLoader(ClassLoader classLoader) {
    log.info("BeanClassLoaderAware setBeanClassLoader()");
  }
}
