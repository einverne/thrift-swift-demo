package info.einverne.springmvcdemo.bean;

/**
 * @since 2020-05-31
 */
public class ClientServiceBean {


  private static ClientServiceBean instance;

  public ClientServiceBean() {
  }

  public static ClientServiceBean createInstance() {
    return instance;
  }
}
