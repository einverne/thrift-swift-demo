package info.einverne.springmvcdemo.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author einverne
 * @date 2019-07-08
 */
@Component
@Scope("prototype")
@Data
public class AnnotationUser implements Validator {

  private String name;
  private int age;

  @Override
  public boolean supports(Class<?> clazz) {
    return AnnotationUser.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmpty(errors, "name", "name is empty");
    AnnotationUser user = (AnnotationUser) target;
    if (user.getAge() < 0) {
      errors.rejectValue("age", "age < 0");
    } else if (user.getAge() > 120) {
      errors.rejectValue("age", "age > 120");
    }
  }
}
