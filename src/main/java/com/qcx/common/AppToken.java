package com.qcx.common;

import com.qcx.common.utils.HttpUtils;
import com.qcx.common.utils.IPUtils;
import com.qcx.common.utils.MD5Utils;
import com.qcx.common.utils.UUIDUtils;

/**
 * 
 * @ClassName: AppToken
 *
 */
public class AppToken {

	/**
	 * thread security of generate token
	 * 
	 * @param request
	 * @return
	 */
	public static synchronized String generateToken() {

		StringBuilder token = new StringBuilder();
		token.append(HttpUtils.getSession().getId());
		token.append("_");
		token.append(UUIDUtils.generateUUID());
		token.append("_");
		token.append(IPUtils.getIpAddr(HttpUtils.getRequest()));
		return MD5Utils.getMD5AndBase64(token.toString());

	}

}
