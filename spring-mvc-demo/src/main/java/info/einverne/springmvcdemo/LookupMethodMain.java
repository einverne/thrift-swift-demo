package info.einverne.springmvcdemo;

import info.einverne.spring.dao.model.User;
import info.einverne.springmvcdemo.iface.AnnotationUserService;
import info.einverne.springmvcdemo.iface.UserService;
import info.einverne.springmvcdemo.model.AnnotationUser;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

/**
 * @author einverne
 * @date 2019-07-05
 */
public class LookupMethodMain {


  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-mvc-context.xml");
    UserService userService = context.getBean("userService", UserService.class);
    User initUserInstance1 = userService.getInitUserInstance();
    User initUserInstance2 = userService.getInitUserInstance();
    Assert.isTrue(initUserInstance1 != initUserInstance2);

    User adminInstance1 = userService.getAdminInstance();
    User adminInstance2 = userService.getAdminInstance();
    Assert.isTrue(adminInstance1 == adminInstance2);

    AnnotationUserService annotationUserService = context.getBean("annotationUserService", AnnotationUserService.class);
    AnnotationUser user1 = annotationUserService.getUser();
    AnnotationUser user2 = annotationUserService.getUser();
    Assert.isTrue(user1 != user2);

    context.close();
  }
}
