package info.einverne.springmvcdemo;

import info.einverne.springmvcdemo.app.Component;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wjw
 * @date 2019-07-05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc-context-test.xml"})
public class ComponentTest {

  /**
   * 通过自定义 XML Schema 方式注入
   */
  @Resource
  private Component component;

  @Test
  public void testCustomXML() {
    System.out.println(component);
  }

}
