package info.einverne.springboot.demo.iface;

import org.springframework.stereotype.Service;

/**
 * @author einverne
 * @date 2019-08-22
 */
@Service
public class FaDataQuery implements DataQuery {


  @Override
  public String get(int type) {
    return "fa";
  }

}
