package com.qcx.code.api;

import com.qcx.code.domain.auth.Resume;
import com.qcx.code.service.IResumeService;
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
 * 简历接口
 * @ClassName: ResumeRest
 */
@RestController
@RequestMapping(value = "/rest/resume")
public class ResumeRest extends BaseRest {

	@Autowired
	private IResumeService resumeService;

	@OpertionBLog(title = "查询简历")
	@Requestauthorize
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public ResponseResult list(@RequestBody Resume resume) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(resumeService.queryAll(resume));
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

	@OpertionBLog(title = "查询个人简历")
	@Requestauthorize
	@RequestMapping(value = "info", method = RequestMethod.POST)
	public ResponseResult info(Resume resume) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(resumeService.query(resume));
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

	@OpertionBLog(title = "添加简历")
	@Requestauthorize
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult add(@RequestBody Resume resume) {
		ResponseResult rep = new ResponseResult();
		try {
			resumeService.add(resume);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("保存异常.请稍后再试");
		}
		return rep;
	}

	@OpertionBLog(title = "修改简历")
	@Requestauthorize
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseResult update(@RequestBody Resume resume) {
		ResponseResult rep = new ResponseResult();
		try {
			resumeService.update(resume);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("修改异常.请稍后再试");
		}
		return rep;
	}

	@OpertionBLog(title = "删除简历")
	@Requestauthorize
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public ResponseResult delete() {
		ResponseResult rep = new ResponseResult();
		try {
			resumeService.delete();
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
