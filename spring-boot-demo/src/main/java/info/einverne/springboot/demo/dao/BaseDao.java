package info.einverne.springboot.demo.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @since 2019-12-16
 */
@Slf4j
@Component
public class BaseDao {
  public void baseGet() {
    log.info("baseGet");
  }

  public void basePut(String p) {
    log.info("basePut: " + p);
  }
}
