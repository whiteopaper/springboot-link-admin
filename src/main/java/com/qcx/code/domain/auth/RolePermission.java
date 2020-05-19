package com.qcx.code.domain.auth;

import com.qcx.core.jdbc.annotation.Columns;
import com.qcx.core.jdbc.annotation.Tables;

@Tables(table = "t_web_role_permission")
public class RolePermission {

	@Columns(column = "role_id")
	private Integer roleId;
	@Columns(column = "perm_id")
	private Integer permId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermId() {
		return permId;
	}

	public void setPermId(Integer permId) {
		this.permId = permId;
	}

	

}