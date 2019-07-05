package info.einverne.springmvcdemo;

import com.google.common.base.CaseFormat;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author einverne
 * @date 2019-07-05
 */
public class UtilsTest {

  @Test
  public void castToLowerCamel() {
    String converted = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, "UtilsTest");
    System.out.println(converted);
    Assert.assertEquals("utilsTest", converted);
  }

}
