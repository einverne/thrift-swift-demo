package info.einverne.springmvcdemo.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author einverne
 * @date 2019-07-05
 *
 * 定义好XML Schema文件，需要定义一个NamespaceHandler解析配置文件。
 * NamespaceHandler 接口需要三个方法:
 *
 * <ul>
 *   <li>init(): NamespaceHandler被使用之前调用，完成NamespaceHandler的初始化</li>
 *   <li>BeanDefinition parse(Element, ParserContext): 当遇到顶层元素时被调用</li>
 *   <li>BeanDefinition decorate(Node,BeanDefinitionHandler,ParserContext): 当遇到一个属性或者嵌套元素的时候调用</li>
 * </ul>
 *
 * Spring提供了一个默认的实现类NamespaceHandlerSupport
 * 
 * NamespaceHandlerSupport可以注册任意个BeanDefinitionParser.
 * NamespaceHandlerSupport负责所有自定义元素的编排，而解析XML的工作委托给各个BeanDefinitionParser负责
 */
public class DateformatNamespaceHandler extends NamespaceHandlerSupport {


  @Override
  public void init() {
    this.registerBeanDefinitionParser("dateformat", new DateformatDefinitionParser());
  }
}
