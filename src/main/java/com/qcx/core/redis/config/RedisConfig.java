package com.qcx.core.redis.config;

//@Configuration
//public class RedisConfig {
//
//	@Bean
//	public RedisConnectionFactory factory() {
//		return new LettuceConnectionFactory();
//	}
//
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
//		template.setConnectionFactory(factory);
//		template.setKeySerializer(new StringRedisSerializer());
//		template.setValueSerializer(new RedisObjectSerializer());
//		return template;
//	}
//
//}
