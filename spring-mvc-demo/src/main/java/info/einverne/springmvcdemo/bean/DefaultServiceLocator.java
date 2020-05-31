package info.einverne.springmvcdemo.bean;

/**
 * @since 2020-05-31
 */
public class DefaultServiceLocator {

  private final static ClientServiceBean bean = new ClientServiceBean();

  public ClientServiceBean createInstance() {
    return bean;
  }
}
