package myWeb;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisTest {
	@Test
	public void test(){
		Jedis jedis = new Jedis("localhost");
		System.out.println(jedis.ping());
	}
}
