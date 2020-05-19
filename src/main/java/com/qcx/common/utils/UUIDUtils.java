package com.qcx.common.utils;

import java.util.UUID;

/**
 * generate only 32char
 *
 */
public class UUIDUtils {

	public static String generateUUID() {
		String uuid = UUID.randomUUID().toString(); // 获取UUID并转化为String对象
		uuid = uuid.replace("-", "");
		return uuid;
	}

}
