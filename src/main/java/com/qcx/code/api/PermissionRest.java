package com.qcx.code.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qcx.code.domain.auth.Permission;
import com.qcx.code.service.IPermissionService;
import com.qcx.common.algorithm.PermissionAlgorithm;
import com.qcx.common.exception.AuthException;
import com.qcx.core.logger.OpertionBLog;
import com.qcx.core.security.authorize.Requestauthorize;
import com.qcx.core.web.mvc.BaseRest;
import com.qcx.core.web.mvc.ResponseResult;

/**
 * 权限接口
 * 
 * @ClassName: PermissionRest
 *
 */
@RestController
@RequestMapping(value = "rest/permission")
public class PermissionRest extends BaseRest {

	@Autowired
	private IPermissionService rightService;

	/**
	 * 获取所有权限
	 * 
	 * @return
	 */
	@Requestauthorize
	@RequestMapping(value = "all")
	public ResponseResult queryAll() {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(PermissionAlgorithm.tree(rightService.queryAll()));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

	/**
	 * 根据角色id获取对应的权限
	 *
	 * @param @param roleId
	 * @param @return 设定文件
	 * @return ResponseResult 返回类型
	 *
	 */
	@RequestMapping(value = "allByRole/{roleId}")
	public ResponseResult queryAllCheckByRole(
			@PathVariable("roleId") Integer roleId) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(PermissionAlgorithm.tree(rightService
					.queryByRole(new Integer[] { roleId })));
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

	@Requestauthorize
	@OpertionBLog(title = "新增权限")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult add(@RequestBody Permission permission) {
		ResponseResult rep = new ResponseResult();
		try {
			rightService.add(permission);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}

		return rep;
	}

	@Requestauthorize
	@OpertionBLog(title = "修改权限")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseResult update(@RequestBody Permission right) {
		ResponseResult rep = new ResponseResult();
		try {
			rightService.update(right);
		} catch (AuthException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

	@Requestauthorize
	@OpertionBLog(title = "删除权限")
	@RequestMapping(value = "delete")
	public ResponseResult delete(@RequestParam("id") Integer id) {
		ResponseResult rep = new ResponseResult();
		try {
			rightService.delete(id);
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
