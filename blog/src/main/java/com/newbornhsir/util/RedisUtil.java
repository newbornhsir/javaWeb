package com.newbornhsir.util;

import redis.clients.jedis.Jedis;

public class RedisUtil {
	public static Jedis connect() {
		Jedis jedis = new Jedis("localhost");
		System.out.println("连接成功");
		return jedis;
	};
	
}
