package info.einverne.springmvcdemo.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author einverne
 * @date 2019-07-08
 */
@Component
@Scope("prototype")
@Data
public class AnnotationUser {

  public String name;
}
