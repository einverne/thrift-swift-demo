package info.einverne.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author einverne
 * @date 2019-08-12
 */
@Controller
public class HelloController {

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String hello() {
    return "hello";
  }
}
