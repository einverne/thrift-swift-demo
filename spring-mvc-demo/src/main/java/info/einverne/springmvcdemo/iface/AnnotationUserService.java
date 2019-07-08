package info.einverne.springmvcdemo.iface;

import info.einverne.springmvcdemo.model.AnnotationUser;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * @author einverne
 * @date 2019-07-08
 */
@Component
public abstract class AnnotationUserService {

  @Lookup(value = "annotationUser")
  public abstract AnnotationUser getUser();

}
