package com.qcx.code.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qcx.code.domain.auth.Department;
import com.qcx.code.service.IDepartmentService;
import com.qcx.common.algorithm.DepartmentAlgorithm;
import com.qcx.common.exception.AuthException;
import com.qcx.core.logger.OpertionBLog;
import com.qcx.core.security.authorize.Requestauthorize;
import com.qcx.core.web.mvc.BaseRest;
import com.qcx.core.web.mvc.ResponseResult;

/**
 * 部门接口
 * 
 * @ClassName: DepartmentRest
 *
 */
@RestController
@RequestMapping(value = "/rest/department")
public class DepartmentRest extends BaseRest {
	@Autowired
	private IDepartmentService departmentService;

	/**
	 * 查询所有部门
	 * 
	 * @return
	 */
	@Requestauthorize
	@RequestMapping(value = "all")
	public ResponseResult queryAll() {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(DepartmentAlgorithm.tree(departmentService.queryAll()));
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
	 * 保存部门
	 * 
	 * @param dept
	 * @return
	 */
	@Requestauthorize
	@OpertionBLog(title = "新增部门")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseResult add(@RequestBody Department dept) {
		ResponseResult rep = new ResponseResult();
		try {
			departmentService.add(dept);
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
	 * 修改
	 * 
	 * @param dept
	 * @return
	 */
	@Requestauthorize
	@OpertionBLog(title = "修改部门")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ResponseResult update(@RequestBody Department dept) {
		ResponseResult rep = new ResponseResult();
		try {
			departmentService.update(dept);
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
	 * 移除
	 * 
	 * @param id
	 * @return
	 */
	@Requestauthorize
	@OpertionBLog(title = "删除部门")
	@RequestMapping(value = "delete")
	public ResponseResult delete(@RequestParam("id") Integer id) {
		ResponseResult rep = new ResponseResult();
		try {
			departmentService.delete(id);
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
