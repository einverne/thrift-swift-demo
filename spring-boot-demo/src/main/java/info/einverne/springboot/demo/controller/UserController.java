package info.einverne.springboot.demo.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demo user Controller
 */
@RestController
@RequestMapping("/user")
public class UserController {
  private static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

  @ApiOperation(value = "获取用户列表", notes = "")
  @RequestMapping(value = {""}, method = RequestMethod.GET)
  public List<User> getUserList() {
    List<User> r = new ArrayList<User>(users.values());
    return r;
  }

  @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
  @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
  @RequestMapping(value = "", method = RequestMethod.POST)
  public String postUser(@RequestBody User user) {
    users.put(user.getId(), user);
    return "success";
  }

  @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
  @ApiParam(name = "id", value = "用户ID", required = true)
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public User getUser(@PathVariable Long id) {
    return users.get(id);
  }

  @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
  @ApiParam(name = "id", value = "用户ID", required = true)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
  })
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public String putUser(@PathVariable Long id, @RequestBody User user) {
    User u = users.get(id);
    u.setName(user.getName());
    u.setAge(user.getAge());
    users.put(id, u);
    return "success";
  }

  @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
  @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public String deleteUser(@PathVariable Long id) {
    users.remove(id);
    return "success";
  }

  @RequestMapping(value = "/test/{id}", method = RequestMethod.POST)
  @ApiOperation(value = "测试接口")
  public String test(@PathVariable("id") String id,
                     @RequestParam("param") String param,
                     @RequestBody User user) {
    return "success id: " + id + " param: " + param + " user: " + user;
  }

  @ApiModel(description = "用户详情")
  @Data
  static class User {
    @ApiModelProperty(value = "用户ID", example = "1234", required = true)
    private long id;
    @ApiModelProperty(value = "用户姓名", example = "王五", required = true)
    private String name;
    @ApiModelProperty(value = "用户年龄", example = "18", required = true)
    private int age;

    public User() {
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public long getId() {
      return id;
    }

    public void setId(long id) {
      this.id = id;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    @Override
    public String
    toString() {
      return "User{" +
          "id=" + id +
          ", name='" + name + '\'' +
          ", age=" + age +
          '}';
    }
  }
}
