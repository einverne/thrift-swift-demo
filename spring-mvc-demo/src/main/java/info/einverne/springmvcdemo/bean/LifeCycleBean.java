package info.einverne.springmvcdemo.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * @since 2020-05-31
 */
@Slf4j
@Repository
public class LifeCycleBean implements BeanNameAware, BeanClassLoaderAware {

  @PostConstruct
  public void init() {
    log.info("PostConstruct");
  }

  @Override
  public void setBeanName(String s) {
    log.info("setBeanName " + s);
  }

  @Override
  public void setBeanClassLoader(ClassLoader classLoader) {
    log.info("setBeanClassLoader " + classLoader);
  }
}
