package com.qcx.code.domain.auth;

import com.qcx.core.jdbc.annotation.Columns;
import com.qcx.core.jdbc.annotation.Tables;

@Tables(table = "t_web_user_role")
public class UserRole {
	@Columns(column = "user_id")
	private String userId;
	@Columns(column = "role_id")
	private Integer roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}