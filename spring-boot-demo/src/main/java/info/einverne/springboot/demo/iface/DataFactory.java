package info.einverne.springboot.demo.iface;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author einverne
 * @date 2019-08-22
 */
@Slf4j
@Service
public class DataFactory {

  @Resource
  private List<DataQuery> dataQueries;

  public String get(int type) {
    StringBuilder sb = new StringBuilder();
    for (DataQuery dataQuery : dataQueries) {
      log.info("data {}", dataQuery);
      sb.append(dataQuery);
    }
    return sb.toString();
  }
}
