package info.einverne.springmvcdemo.spring;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import java.text.SimpleDateFormat;

/**
 * @author einverne
 * @date 2019-07-05
 */
public class DateformatDefinitionParser extends AbstractSingleBeanDefinitionParser {

  //返回元素的Class类型
  @Override
  protected Class<?> getBeanClass(Element element) {
    return SimpleDateFormat.class;
  }

  //添加元素的属性或者构造参数等等
  @Override
  protected void doParse(Element element, BeanDefinitionBuilder builder) {
    String pattern = element.getAttribute("pattern");
    builder.addConstructorArgValue(pattern);
  }
}
