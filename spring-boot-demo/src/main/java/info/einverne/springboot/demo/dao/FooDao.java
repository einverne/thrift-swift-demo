package info.einverne.springboot.demo.dao;

import info.einverne.springboot.demo.annotation.AutoLog;
import info.einverne.springboot.demo.model.ArgsParam;
import lombok.extern.slf4j.Slf4j;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @since 2019-12-12
 */
@Slf4j
@Component
public class FooDao extends BaseDao {

  public String get(long id) {
    baseGet();
    return "some name";
  }

  public List<String> getList(int a, String b) {
    return Lists.newArrayList();
  }

  public String anotherGet(String name) {
    return "other info";
  }

  public void getByArgsParam(ArgsParam argsParam) {
    System.out.println(argsParam);
  }
}
