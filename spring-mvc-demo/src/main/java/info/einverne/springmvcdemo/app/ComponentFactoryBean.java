package info.einverne.springmvcdemo.app;

import org.springframework.beans.factory.FactoryBean;

import java.util.List;

/**
 * @author einverne
 * @date 2019-07-05
 */
public class ComponentFactoryBean implements FactoryBean<Component> {

  private Component parent;

  private List<Component> children;

  @Override
  public Component getObject() throws Exception {
    if (this.children != null && this.children.size() > 0) {
      for (Object child : children) {
        Component childComponent = (Component) child;
        this.parent.addComponent(childComponent);
      }
    }
    return this.parent;
  }

  @Override
  public Class<?> getObjectType() {
    return Component.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

  public void setParent(Component parent) {
    this.parent = parent;
  }

  public void setChildren(List<Component> children) {
    this.children = children;
  }

  public Component getParent() {
    return parent;
  }

  public List<Component> getChildren() {
    return children;
  }
}
