package com.qcx.code.dao;

import java.util.List;

import com.qcx.code.domain.auth.Role;
import com.qcx.code.domain.auth.RoleDept;
import com.qcx.code.domain.auth.RolePermission;
import com.qcx.core.web.mvc.JqGridPage;

public interface IRoleDao {
	JqGridPage<Role> selectPage(Role role);

	List<Role> selectByUserId(String userId);

	Role select(Integer id);

	List<Role> select(Role role);

	int insertRetrunId(Role role);

	int insert(Role role);

	int update(Role role);

	int delete(Role role);

	int[] insert(List<RolePermission> rpList);

	int delete(RolePermission roleRelationRight);

	int[] insertRoleDetp(List<RoleDept> list);

	int deleteRoleDetp(RoleDept roleDept);

	List<Integer> selectRoleDetp(Integer roleId);

}
