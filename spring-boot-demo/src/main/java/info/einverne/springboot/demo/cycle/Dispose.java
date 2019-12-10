package info.einverne.springboot.demo.cycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * @author einverne
 * @since 2019-12-10
 */
@Slf4j
@Component
public class Dispose implements DisposableBean {


  @Override
  public void destroy() throws Exception {
    log.info("DisposableBean's destroy()");
  }
}
