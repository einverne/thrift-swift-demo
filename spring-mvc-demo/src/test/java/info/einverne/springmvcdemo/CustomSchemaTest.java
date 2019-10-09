package info.einverne.springmvcdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author einverne
 * @date 2019-07-05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc-context-test.xml"})
public class CustomSchemaTest {

  /**
   * 通过自定义 XML Schema 方式注入 dataFormat
   */
  @Resource
  private SimpleDateFormat dateFormat;

  @Test
  public void testCustomXML() {
    String date = "2019-07-02 10:20:02";

    try {
      Date parsed = dateFormat.parse(date);
      System.out.println(parsed);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

}
