package info.einverne.spring.swift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mi on 17-9-28.
 */
@SpringBootApplication
@RestController
public class SpringBootSwiftApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSwiftApplication.class, args);
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}
