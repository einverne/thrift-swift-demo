package info.einverne.springboot.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author einverne
 * @date 2019-04-03
 */
@Slf4j
@Service
public class HelloService {

  public boolean login(String name, String password) {
    log.info("hello");
    return true;
  }

}
