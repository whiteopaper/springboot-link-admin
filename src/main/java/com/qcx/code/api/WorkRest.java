package com.qcx.code.api;

import com.qcx.code.domain.auth.Work;
import com.qcx.code.service.IWorkService;
import com.qcx.common.exception.AuthException;
import com.qcx.core.logger.LoggerUtil;
import com.qcx.core.logger.OpertionBLog;
import com.qcx.core.security.authorize.Requestauthorize;
import com.qcx.core.web.mvc.BaseRest;
import com.qcx.core.web.mvc.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工作接口
 * @ClassName: WorkRest
 */
@RestController
@RequestMapping(value = "/rest/work")
public class WorkRest extends BaseRest {

	@Autowired
	private IWorkService workService;

	@OpertionBLog(title = "查询工作")
	@Requestauthorize
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public ResponseResult list(@RequestBody Work work) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(workService.queryAll(work));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
			LoggerUtil.error(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			LoggerUtil.error(e.getMessage());
			rep.setMsg("查询异常.请稍后再试");
		}
		return rep;

	}

	@OpertionBLog(title = "查询企业工作")
	@Requestauthorize
	@RequestMapping(value = "info", method = RequestMethod.POST)
	public ResponseResult info(Work work) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(workService.query(work));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
			LoggerUtil.error(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			LoggerUtil.error(e.getMessage());
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;

	}

	@OpertionBLog(title = "添加工作")
	@Requestauthorize
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult add(@RequestBody Work work) {
		ResponseResult rep = new ResponseResult();
		try {
			workService.add(work);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("保存异常.请稍后再试");
		}
		return rep;
	}

	@OpertionBLog(title = "修改工作")
	@Requestauthorize
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseResult update(@RequestBody Work work) {
		ResponseResult rep = new ResponseResult();
		try {
			workService.update(work);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("修改异常.请稍后再试");
		}
		return rep;
	}

	@OpertionBLog(title = "删除工作")
	@Requestauthorize
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public ResponseResult delete() {
		ResponseResult rep = new ResponseResult();
		try {
			workService.delete();
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("删除异常.请稍后再试");
		}
		return rep;
	}


}
