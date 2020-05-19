package com.qcx.code.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcx.code.dao.IUserDao;
import com.qcx.code.domain.auth.Department;
import com.qcx.code.domain.auth.LoginVO;
import com.qcx.code.domain.auth.ModifyPwdVO;
import com.qcx.code.domain.auth.Permission;
import com.qcx.code.domain.auth.Role;
import com.qcx.code.domain.auth.UserInfo;
import com.qcx.code.domain.auth.UserInfoVO;
import com.qcx.code.domain.auth.UserRole;
import com.qcx.code.service.IDepartmentService;
import com.qcx.code.service.IPermissionService;
import com.qcx.code.service.IRoleService;
import com.qcx.code.service.IUserService;
import com.qcx.common.AppToken;
import com.qcx.common.GlobalUser;
import com.qcx.common.algorithm.PermissionAlgorithm;
import com.qcx.common.exception.AuthException;
import com.qcx.common.utils.BeanUtils;
import com.qcx.common.utils.MD5Utils;
import com.qcx.common.utils.StringUtils;
import com.qcx.common.utils.UUIDUtils;
import com.qcx.core.web.mvc.JqGridPage;

@Service
public class UserService implements IUserService {
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPermissionService rightService;
	@Autowired
	private IDepartmentService departmentService;

	@Override
	public String login(LoginVO vo) {
		validateLoginCodition(vo);
		UserInfo userInfo = loginProcess(vo);
		String token = AppToken.generateToken();
		userInfo.setToken(token);
		GlobalUser.setUserInfo(userInfo);
		return token;
	}

	private void validateLoginCodition(LoginVO vo) {
		if (vo == null) {
			throw new AuthException("登录失败");
		}
		if (StringUtils.isBlank(vo.getUsername())) {
			throw new AuthException("用户名不能为空");
		}
		if (StringUtils.isBlank(vo.getPassword())) {
			throw new AuthException("密码不能为空");
		}
	}

	private UserInfo loginProcess(LoginVO vo) {
		UserInfo param = new UserInfo();
		param.setName(vo.getUsername());
		// 根据登陆名
		UserInfo user = userDao.find(param);
		if (user == null) {
			throw new AuthException("无效的用户名");
		}
		String md5password = MD5Utils.getMD5Encoding(vo.getPassword());
		if (!user.getPassword().equals(md5password)) {
			throw new AuthException("无效的用户名或密码");
		}
		if (GlobalUser.user_unable.equals(user.getState())) {
			List<Role> list = roleService.queryByUser(user.getUid());
			if (list != null){
				for ( Role i : list){
					//注册用户跳过禁用可登录  待验证
					if (i.getId() == 3){
						return user;
					}
				}
			}
			throw new AuthException("该账户已被禁用");
		}
		return user;
	}

	@Override
	public UserInfo info() {
		// 判断用户数据是否为空
		UserInfo user = GlobalUser.getUserInfo();
		if (user == null) {
			throw new AuthException("用户未登录");
		}
		// 部门
		if (user.getDeptid() !=null){
			Department dept = departmentService.query(user.getDeptid());
			if (dept == null) {
				throw new AuthException("未查询到当前用户的部门");
			}
			user.setDeptName(dept.getName());
		}
		// 角色
		List<Role> roles = roleService.queryByUser(user.getUid());
		if (roles == null || roles.isEmpty()) {
			throw new AuthException("当前用户未分配角色");
		}

		List<Integer> roleIds = new ArrayList<Integer>();
		for (Role role : roles) {
			roleIds.add(role.getId());
		}
		// 角色对应的权限
		List<Permission> permissionList = rightService.queryByRole(roleIds
				.toArray(new Integer[roleIds.size()]));

		if (permissionList == null || permissionList.isEmpty()) {
			throw new AuthException("当前用户为的角色未分配权限");
		}
		// 用户菜单
		List<Permission> menus = new ArrayList<Permission>();
		// 用户拥有的功能权限
		List<Permission> permissions = new ArrayList<Permission>();
		for (Permission perm : permissionList) {
			if (perm.isMenu()) {
				menus.add(perm);
			}
			if (perm.isPermission()) {
				permissions.add(perm);
			}
		}
		menus = PermissionAlgorithm.tree(menus);

		user.setRoles(roles);
		user.setMenus(PermissionAlgorithm.buildMenu(menus));
		user.setPermissions(permissions);
		GlobalUser.setUserInfo(user);
		return user;
	}

	@Override
	public void modifyPwd(ModifyPwdVO vo) {

		if (vo == null || StringUtils.isBlank(vo.getOldPassword())
				|| StringUtils.isBlank(vo.getNewPassword())
				|| StringUtils.isBlank(vo.getConfirmNewPassword())) {
			throw new AuthException("必填项不能为空");
		}
		if (!vo.getNewPassword().equals(vo.getConfirmNewPassword())) {
			throw new AuthException("两次输入密码必须相同");
		}
		UserInfo user = GlobalUser.getUserInfo();

		if (!user.getPassword().equals(
				MD5Utils.getMD5Encoding(vo.getOldPassword()))) {
			throw new AuthException("原密码错误");
		}

		UserInfo userInfo = userDao.select(user.getUid());
		if (userInfo == null) {
			throw new AuthException("用户不存在");
		}
		userInfo.setPassword(MD5Utils.getMD5Encoding(vo.getNewPassword()));
		userDao.update(userInfo);
		// 更新内存中的密码
		GlobalUser.setUserInfo(userInfo);

	}

	@Override
	public JqGridPage<UserInfo> queryPage(UserInfo user) {
		if (user == null) {
			throw new AuthException("参数不能为空");
		}
		JqGridPage<UserInfo> page = userDao.selectPage(user);
		if (page.getRows() != null && !page.getRows().isEmpty()) {
			for (UserInfo userInfo : page.getRows()) {
				List<Role> roleList = roleService
						.queryByUser(userInfo.getUid());
				userInfo.setRoles(roleList);
			}
		}
		return page;
	}

	@Transactional(value = "baseTransactionManager")
	@Override
	public boolean add(UserInfoVO vo) throws AuthException {

		if (StringUtils.isBlank(vo.getVserName())) {
			throw new AuthException("真实姓名不能为空");
		}
		if (StringUtils.isBlank(vo.getName())) {
			throw new AuthException("登陆名不能为空");
		}
		if (StringUtils.isBlank(vo.getPassword())) {
			throw new AuthException("登陆密码不能为空");
		}
		if (vo.getDeptid() == null) {
			throw new AuthException("请选择部门");
		}
		UserInfo user = new UserInfo();
		BeanUtils.copyObject(user, vo);
		user.setCreateTime(new Date());
		String password = MD5Utils.getMD5Encoding(user.getPassword());
		user.setPassword(password);
		user.setUid(UUIDUtils.generateUUID());
		// 保存用户信息
		int result = userDao.insert(user);
		if (result < 0) {
			throw new AuthException("保存失败");
		}

		if (vo.getRoleIds() != null && vo.getRoleIds().length > 0) {
			List<UserRole> urList = new ArrayList<UserRole>();
			UserRole ur;
			for (Integer roleId : vo.getRoleIds()) {
				ur = new UserRole();
				ur.setUserId(user.getUid());
				ur.setRoleId(roleId);
				urList.add(ur);
			}
			// 保存用户分配的角色
			saveRelationRole(urList);
		}
		return true;
	}

	@Transactional(value = "baseTransactionManager")
	@Override
	public boolean register(UserInfoVO vo) throws AuthException {

		vo.setState(GlobalUser.user_unable);
		Integer[] roleIds = new Integer[1];
		roleIds[0] = 3;// 注册用户角色
		vo.setRoleIds(roleIds);

		if (StringUtils.isBlank(vo.getMobile())) {
			throw new AuthException("手机号不能为空");
		}
		if (StringUtils.isBlank(vo.getName())) {
			throw new AuthException("登陆名不能为空");
		}
		if (StringUtils.isBlank(vo.getPassword())) {
			throw new AuthException("登陆密码不能为空");
		}
		UserInfo param = new UserInfo();
		param.setName(vo.getName());
		UserInfo u = userDao.find(param);
		if (u != null) {
			throw new AuthException("账号已存在");
		}
		param = new UserInfo();
		param.setMobile(vo.getMobile());
		u = userDao.find(param);
		if (u != null) {
			throw new AuthException("手机号已存在");
		}
		UserInfo user = new UserInfo();
		BeanUtils.copyObject(user, vo);
		user.setCreateTime(new Date());
		String password = MD5Utils.getMD5Encoding(user.getPassword());
		user.setPassword(password);
		user.setUid(UUIDUtils.generateUUID());
		// 保存用户信息
		int result = userDao.insert(user);
		if (result < 0) {
			throw new AuthException("注册失败");
		}
		// 保存角色信息
		if (vo.getRoleIds() != null && vo.getRoleIds().length > 0) {
			List<UserRole> urList = new ArrayList<UserRole>();
			UserRole ur;
			for (Integer roleId : vo.getRoleIds()) {
				ur = new UserRole();
				ur.setUserId(user.getUid());
				ur.setRoleId(roleId);
				urList.add(ur);
			}
			// 保存用户分配的角色
			saveRelationRole(urList);
		}
		return true;
	}

	@Override
	public boolean modifyPersonalInfo(UserInfoVO vo) {
		// 判断用户数据是否为空
		UserInfo user = GlobalUser.getUserInfo();
		if (user == null) {
			throw new AuthException("用户未登录");
		}
		BeanUtils.copyObject(user, vo);
		BeanUtils.copyObject(vo, user);
		update(vo,false);
		//刷新redis当前用户
		UserInfo param = new UserInfo();
		param.setName(vo.getName());
		UserInfo userInfo = userDao.find(param);
		userInfo.setToken(user.getToken());
		GlobalUser.setUserInfo(userInfo);
		return true;
	}

	@Override
	public boolean isRole(int rid) {
		UserRole userRelationRole = new UserRole();
		UserInfo user = GlobalUser.getUserInfo();
		userRelationRole.setUserId(user.getUid());
		userRelationRole.setRoleId(rid);
		if (userDao.select(userRelationRole)!=null){
			return true;
		}
		return false;
	}

	@Override
	public boolean update(UserInfoVO vo,boolean type) throws AuthException {

		if (vo.getUid() == null) {
			throw new AuthException("用户不存在");
		}
		if (vo.getDeptid() == null) {
			throw new AuthException("请选择部门");
		}
		UserInfo user = userDao.select(vo.getUid());
		if (user == null) {
			throw new AuthException("用户不存在");
		}
		String password = "";
		if (!user.getPassword().equals(vo.getPassword())) {
			password = MD5Utils.getMD5Encoding(vo.getPassword());
		}
		BeanUtils.copyObject(user, vo);
		if (StringUtils.isNotBlank(password)) {
			user.setPassword(password);
		}
		int result = userDao.update(user);
		if (result < 0) {
			throw new AuthException("修改失败");
		}

		if (type){
			// 删除用户角色
			UserRole delUr = new UserRole();
			delUr.setUserId(user.getUid());
			userDao.delete(delUr);
			// 重新角色信息
			if ((vo.getRoleIds() != null && vo.getRoleIds().length > 0)) {
				List<UserRole> urList = new ArrayList<UserRole>();
				UserRole ur;
				for (Integer roleId : vo.getRoleIds()) {
					ur = new UserRole();
					ur.setUserId(user.getUid());
					ur.setRoleId(roleId);
					urList.add(ur);
				}
				// 保存用户分配的角色
				saveRelationRole(urList);
			}
		}
		return true;

	}

	public boolean saveRelationRole(List<UserRole> urList) {
		userDao.insert(urList);
		return true;
	}

	@Override
	public boolean delete(String uid) throws AuthException {
		if (StringUtils.isBlank(uid)) {
			throw new AuthException("uid不能为空");
		}
		UserInfo user = new UserInfo();
		user.setUid(uid);

		int result = userDao.delete(user);
		if (result < 0) {
			throw new AuthException("删除失败");
		}
		return true;
	}

	@Override
	public UserInfo find(String uid) {
		if (StringUtils.isBlank(uid)) {
			return null;
		}
		UserInfo userInfo = userDao.select(uid);
		return userInfo;
	}

	@Override
	public UserInfo find(UserInfo user) {
		return userDao.find(user);
	}

	/**
	 * 更新用户状态
	 */
	@Override
	public boolean updateState(UserInfoVO vo) {
		if (StringUtils.isBlank(vo.getUid())) {
			throw new AuthException("uid不能为空");
		}
		int result = userDao.updateState(vo.getUid(), vo.getState());
		if (result < 0) {
			throw new AuthException("更新失败");
		}
		return true;
	}
}
