package info.einverne.springmvcdemo;

import info.einverne.spring.dao.mapper.UserMapper;
import info.einverne.spring.dao.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * @author wjw
 * @date 2019-07-08
 */
@Controller
public class UserController {

  @Resource
  private UserMapper userMapper;

  public String addUser(@RequestBody User user) {
    userMapper.insert(user);
    return "success";
  }


}
