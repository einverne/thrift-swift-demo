package info.einverne.springmvcdemo.iface;

import info.einverne.spring.dao.model.User;

/**
 * @author einverne
 * @date 2019-07-08
 */
public abstract class UserService {

  /**
   * always return new instance
   *
   * @return
   */
  public abstract User getInitUserInstance();

  /**
   * singleton , always return the same object
   *
   * @return
   */
  public abstract User getAdminInstance();

}
