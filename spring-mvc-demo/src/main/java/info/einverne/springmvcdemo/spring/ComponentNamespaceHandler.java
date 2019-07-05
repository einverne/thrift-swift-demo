package info.einverne.springmvcdemo.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author einverne
 * @date 2019-07-05
 */
public class ComponentNamespaceHandler extends NamespaceHandlerSupport {


  @Override
  public void init() {
    this.registerBeanDefinitionParser("component", new ComponentDefinitionParser());
  }
}
