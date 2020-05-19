package com.qcx.code.api;

import com.qcx.code.domain.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qcx.code.service.IUserService;
import com.qcx.common.GlobalUser;
import com.qcx.common.exception.AuthException;
import com.qcx.core.logger.LoggerUtil;
import com.qcx.core.logger.OpertionBLog;
import com.qcx.core.security.authorize.Requestauthorize;
import com.qcx.core.security.requestlimt.RequestLimit;
import com.qcx.core.web.mvc.BaseRest;
import com.qcx.core.web.mvc.ResponseResult;

/**
 * 用户接口
 * @ClassName: UserRest
 */
@RestController
@RequestMapping(value = "/rest/user")
public class UserRest extends BaseRest {

	@Autowired
	private IUserService userService;

	// 一分钟请求100次，等待300秒
	@RequestLimit(time = 60, count = 100, waits = 300)
	@OpertionBLog(title = "登录")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseResult login(@RequestBody LoginVO vo) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(userService.login(vo));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("登陆异常.请稍后再试");
		}
		return rep;
	}

	/**
	 * 当前在线用户的信息包括角色权限菜单等
	 *
	 * @param @return 设定文件
	 * @return ResponseResult 返回类型
	 *
	 */
	@RequestMapping(value = "info")
	public ResponseResult info() {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(userService.info());
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
			LoggerUtil.error(e.getMessage());
		}
		return rep;
	}

	// 一分钟请求100次，等待300秒
	@RequestLimit(time = 60, count = 100, waits = 300)
	@OpertionBLog(title = "注册")
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ResponseResult register(@RequestBody UserInfoVO user) {
		ResponseResult rep = new ResponseResult();
		try {
			userService.register(user);
			LoginVO vo = new LoginVO();
			vo.setUsername(user.getName());
			vo.setPassword(user.getPassword());
			rep.setResult(userService.login(vo));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("注册异常.请稍后再试");
		}
		return rep;
	}

	@OpertionBLog(title = "修改个人信息")
	@Requestauthorize
	@RequestMapping(value = "modifyPersonalInfo", method = RequestMethod.POST)
	public ResponseResult modifyPersonalInfo(@RequestBody UserInfoVO user) {
		ResponseResult rep = new ResponseResult();
		try {
			userService.modifyPersonalInfo(user);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("修改异常.请稍后再试");
		}
		return rep;
	}

	@OpertionBLog(title = "判断角色")
	@Requestauthorize
	@RequestMapping(value = "isRole" , method = RequestMethod.POST)
	public ResponseResult isRole(@RequestBody int rid) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(userService.isRole(rid));
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

	@OpertionBLog(title = "退出")
	@RequestMapping("logout")
	public ResponseResult logout() {
		ResponseResult rep = new ResponseResult();
		try {
			GlobalUser.destroyUser();
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

	@OpertionBLog(title = "用户列表")
	@Requestauthorize
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public ResponseResult list(@RequestBody UserInfo user) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(userService.queryPage(user));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			LoggerUtil.error(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			LoggerUtil.error(e.getMessage());
		}
		return rep;

	}

	@OpertionBLog(title = "添加用户")
	@Requestauthorize
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult add(@RequestBody UserInfoVO user) {
		ResponseResult rep = new ResponseResult();
		try {
			userService.add(user);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("保存异常.请稍后再试");
		}
		return rep;
	}

	@OpertionBLog(title = "修改用户")
	@Requestauthorize
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseResult update(@RequestBody UserInfoVO user) {
		ResponseResult rep = new ResponseResult();
		try {
			userService.update(user,true);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("修改异常.请稍后再试");
		}
		return rep;
	}

	@OpertionBLog(title = "修改密码")
	@RequestMapping(value = "modifyPwd", method = RequestMethod.POST)
	public ResponseResult modifyPwd(@RequestBody ModifyPwdVO vo) {
		ResponseResult rep = new ResponseResult();
		try {
			userService.modifyPwd(vo);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;

	}

	@OpertionBLog(title = "更新用户状态")
	@RequestMapping(value = "updateState", method = RequestMethod.POST)
	public ResponseResult updateState(@RequestBody UserInfoVO vo) {
		ResponseResult rep = new ResponseResult();
		try {
			userService.updateState(vo);
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
