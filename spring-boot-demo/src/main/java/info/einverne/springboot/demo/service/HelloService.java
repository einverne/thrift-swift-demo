package info.einverne.springboot.demo.service;

import org.springframework.stereotype.Service;

/**
 * @author wjw
 * @date 2019-04-03
 */
@Service
public class HelloService {

  public boolean login(String name, String password) {
    System.out.println("hello");
    return true;
  }

}
