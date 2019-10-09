package info.einverne.spring.dao.mapper;

import info.einverne.spring.dao.model.User;
import info.einverne.spring.dao.model.UserExample;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author einverne
 * @date 2019-07-08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc-context-test.xml"})
public class UserMapperTest {

  @Resource
  private UserMapper userMapper;


  @Test
  public void add() {
    User record = new User();
    record.setNickname("einverne");
    record.setAvatar("ev");
    record.setGender("m");
    userMapper.insert(record);

    List<User> users = userMapper.selectByExample(new UserExample());
    Assert.assertTrue(users.size() == 1);
  }

}