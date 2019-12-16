package info.einverne.springboot.demo.controller;

import info.einverne.springboot.demo.annotation.AutoLog;
import info.einverne.springboot.demo.annotation.Intercepted;
import info.einverne.springboot.demo.dao.AnnotatedClass;
import info.einverne.springboot.demo.dao.AnnotatedMethod;
import info.einverne.springboot.demo.dao.AnnotatedMix;
import info.einverne.springboot.demo.dao.FooDao;
import info.einverne.springboot.demo.model.ArgsParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @since 2019-12-12
 */
@RestController
@RequestMapping("/foo")
public class FooController {

  @Resource
  private FooDao fooDao;

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public String hello() {
    String data = fooDao.get(0L);

    fooDao.getByArgsParam(new ArgsParam());
    return data;
  }

  @RequestMapping(value = "/get1", method = RequestMethod.GET)
  public String hello1() {
    String data = fooDao.get(0L);
    return data;
  }

  @RequestMapping(value = "/getInter", method = RequestMethod.GET)
  public String testIntercept() {
    new AnnotatedClass().doSomething();
    new AnnotatedClass().doSomethingElse();

    new AnnotatedMethod().doSomething();
    new AnnotatedMethod().doSomethingElse();

    new AnnotatedMix().doSomething();
    new AnnotatedMix().doSomethingElse();
    return "intercept";
  }
}
