package com.qcx.core.redis;

import org.springframework.stereotype.Component;

import com.qcx.core.web.SpringUtils;

/**
 * 
 * @ClassName: RedisUtils
 *
 */
@Component
public class RedisUtils {
	// cache
	private static volatile IRedis redis = null;

	public static IRedis getRedis() {
		if (redis == null) {
			redis = SpringUtils.getBean(IRedis.class);
			;
		}
		return redis;
	}

}
