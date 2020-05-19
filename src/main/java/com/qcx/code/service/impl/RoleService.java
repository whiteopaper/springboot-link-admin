package com.qcx.code.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcx.code.dao.IPermissionDao;
import com.qcx.code.dao.IRoleDao;
import com.qcx.code.dao.IUserDao;
import com.qcx.code.domain.auth.Role;
import com.qcx.code.domain.auth.RoleDept;
import com.qcx.code.domain.auth.RolePermission;
import com.qcx.code.service.IRoleService;
import com.qcx.common.exception.AuthException;
import com.qcx.common.utils.BeanUtils;
import com.qcx.common.utils.StringUtils;
import com.qcx.core.web.mvc.JqGridPage;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private IRoleDao roleDao;

	@Override
	public JqGridPage<Role> queryPage(Role role) {
		return roleDao.selectPage(role);
	}

	@Override
	public List<Role> queryAll() throws AuthException {
		return roleDao.select(new Role());
	}

	@Override
	public List<Role> queryByUser(String userId) throws AuthException {
		if (StringUtils.isBlank(userId)) {
			throw new AuthException("userId不能为空");
		}
		return roleDao.selectByUserId(userId);
	}

	@Transactional(value = "baseTransactionManager")
	@Override
	public boolean add(Role role) throws AuthException {
		if (role == null) {
			throw new AuthException("保存数据不能为空");
		}
		if (StringUtils.isBlank(role.getName())) {
			throw new AuthException("角色名不能为空");
		}
		int result = roleDao.insertRetrunId(role);

		if (role.getPermIds() != null && role.getPermIds().length > 0) {
			role.setId(result);
			saveRelationPermission(role);
		}
		return true;
	}

	@Transactional(value = "baseTransactionManager")
	@Override
	public boolean update(Role role) throws AuthException {

		if (role == null) {
			throw new AuthException("保存数据不能为空");
		}
		if (role.getId() == null) {
			throw new AuthException("保存数据不能为空");
		}
		if (StringUtils.isBlank(role.getName())) {
			throw new AuthException("角色名不能为空");
		}
		Role roleInfo = roleDao.select(role.getId());
		if (roleInfo == null) {
			throw new AuthException("未查询到角色信息");
		}
		BeanUtils.copyObject(roleInfo, role);
		roleDao.update(roleInfo);

		RolePermission rp = new RolePermission();
		rp.setRoleId(role.getId());
		roleDao.delete(rp);
		if (role.getPermIds() != null && role.getPermIds().length > 0) {
			saveRelationPermission(role);
		}
		return true;
	}

	public boolean saveRelationPermission(Role role) {
		List<RolePermission> rpList = new ArrayList<RolePermission>();
		RolePermission rp = null;
		for (int i = 0; i < role.getPermIds().length; i++) {
			rp = new RolePermission();
			rp.setRoleId(role.getId());
			rp.setPermId(role.getPermIds()[i]);
			rpList.add(rp);
		}
		roleDao.insert(rpList);
		return true;
	}

	@Transactional(value = "baseTransactionManager")
	@Override
	public boolean delete(Integer code) throws AuthException {
		if (code == null) {
			throw new AuthException("删除条件不能为空");
		}
		Role role = new Role();
		role.setId(code);
		int result = 0;
		// 删除角色本身
		result = roleDao.delete(role);
		if (result < 0) {
			throw new AuthException("执行失败");
		}

		// 删除角色对应的权限
		RolePermission roleRight = new RolePermission();
		roleRight.setRoleId(code);
		roleDao.delete(roleRight);

		return true;
	}

	@Override
	public Role query(Integer code) throws AuthException {

		Role role = new Role();
		role.setId(code);
		List<Role> list = roleDao.select(role);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
