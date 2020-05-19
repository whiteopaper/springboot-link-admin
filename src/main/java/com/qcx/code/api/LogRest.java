package com.qcx.code.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qcx.code.domain.logs.BLogVO;
import com.qcx.code.service.ILogService;
import com.qcx.common.exception.AuthException;
import com.qcx.core.security.authorize.Requestauthorize;
import com.qcx.core.web.mvc.BaseRest;
import com.qcx.core.web.mvc.ResponseResult;
/**
 * 日志接口
* @ClassName: LogRest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 252956
* @date 2019年10月21日 下午4:56:10 
*
 */
@RestController
@RequestMapping(value = "/rest/logs")
public class LogRest extends BaseRest {
	@Autowired
	private ILogService logService;

	@Requestauthorize
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseResult list(@RequestBody BLogVO vo) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(logService.queryPage(vo));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;

	}
}
