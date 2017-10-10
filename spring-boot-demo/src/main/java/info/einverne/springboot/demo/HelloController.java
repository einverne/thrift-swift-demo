package info.einverne.springboot.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 注解 @RestController 是 @Controller 和 @ResponseBody 的结合
 * Controller 中的每一个方法都是返回 rest object 而不是 View
 */
@RestController
public class HelloController {

    @Value("${key.hello}")
    private String hello;

    @Autowired
    private Person person;

    @RequestMapping("/hello")
    public String hello() {
        return "world " + hello;
    }

    @RequestMapping("/person")
    public String person() {
        return "person info: " + person.getName() + " " + person.getAge();
    }
}
