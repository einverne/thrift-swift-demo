package info.einverne.springmvcdemo;

import info.einverne.springmvcdemo.app.Component;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author einverne
 * @date 2019-07-05
 */
public class CustomNamespaceMain {


  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-mvc-context.xml");
    SimpleDateFormat dateformat = context.getBean("dateformat", SimpleDateFormat.class);

    try {
      Date parse = dateformat.parse("2019-07-06 10:02");
      System.out.println(parse);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    Component component = context.getBean(Component.class);
    System.out.println(component);
  }
}
