package info.einverne.springboot.demo.cycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author einverne
 * @since 2019-12-10
 */
@Slf4j
@Component
public class Init implements InitializingBean {

  @Override
  public void afterPropertiesSet() throws Exception {
    log.info("InitializingBean's afterPropertiesSet()");
  }
}
