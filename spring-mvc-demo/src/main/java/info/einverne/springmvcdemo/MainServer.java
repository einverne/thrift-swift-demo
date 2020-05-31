package info.einverne.springmvcdemo;

import info.einverne.spring.dao.model.User;
import info.einverne.springmvcdemo.iface.AnnotationUserService;
import info.einverne.springmvcdemo.iface.UserService;
import info.einverne.springmvcdemo.model.AnnotationUser;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

/**
 * @since 2020-05-31
 */
public class MainServer {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-mvc-context.xml");
    context.registerShutdownHook();
  }


}
