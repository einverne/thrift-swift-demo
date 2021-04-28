package info.einverne.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @since 2021-04-28
 */
public class JedisDemoTest {
  private static final String TEST_KEY = "test.key";

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  /**
   * Open Socket to communicate with redis
   */
  @Test
  public void basicTest() {
    Jedis jedis = new Jedis(Protocol.DEFAULT_HOST, Protocol.DEFAULT_PORT);
//    jedis.auth("password");
    String setResult = jedis.set(TEST_KEY, "jedis.test");
    System.out.println(setResult);
    String getResult = jedis.get(TEST_KEY);
    System.out.println(getResult);
    jedis.close();
  }

  @Test
  public void poolTest() {
    JedisPoolConfig config = new JedisPoolConfig();
    config.setMaxTotal(20);
    config.setMaxIdle(10);
    JedisPool jedisPool = new JedisPool(config, "localhost", 6379, 2000);
    Jedis jedis = jedisPool.getResource();
    String value = jedis.get(TEST_KEY);
    System.out.println(value); // 连接返回给连接池
    jedis.close();
    jedisPool.close();
  }
}