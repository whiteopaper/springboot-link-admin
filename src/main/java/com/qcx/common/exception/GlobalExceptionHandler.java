package com.qcx.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qcx.common.AppContext;
import com.qcx.core.web.mvc.ResponseResult;

/**
 * 全局异常
 * 
 * @ClassName: GlobalExceptionHandler
 *
 */
@ControllerAdvice
class GlobalExceptionHandler {

	// json exceptin
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseResult jsonErrorHandler(HttpServletRequest req, Exception e)
			throws Exception {
		ResponseResult r = new ResponseResult();
		r.setCode(AppContext.CODE_50000);
		r.setMsg("系统异常");
		return r;
	}
}