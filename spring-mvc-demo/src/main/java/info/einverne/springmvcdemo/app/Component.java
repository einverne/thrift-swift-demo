package info.einverne.springmvcdemo.app;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author einverne
 * @date 2019-07-05
 */
@EqualsAndHashCode
public class Component {

  private String name;

  private List<Component> components = Lists.newArrayList();

  public void addComponent(Component c) {
    components.add(c);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List getComponents() {
    return components;
  }

  public void setComponents(List<Component> components) {
    this.components = components;
  }

  @Override
  public String toString() {
    return "Component{" +
        "name='" + name + '\'' +
        ", components=" + components +
        '}';
  }
}
