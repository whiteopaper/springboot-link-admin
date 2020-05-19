package com.qcx.code.domain.auth;

import com.qcx.core.jdbc.annotation.Columns;
import com.qcx.core.jdbc.annotation.Tables;
import com.qcx.core.web.mvc.JqGridParam;

/**
 * 角色表
 * 
 * @ClassName: Role
 *
 */
@Tables(table = "t_web_role")
public class Role extends JqGridParam {

	@Columns(column = "id", primaryKey = true)
	private Integer id;
	@Columns(column = "name")
	private String name;
	@Columns(column = "levels")
	private Integer levels;// 新增用户时只能赋予比自己级别低的角色

	@Columns(column = "description")
	private String description;

	// 自定义数据权限
	private Integer deptIds[];
	// 权限id
	private Integer permIds[];

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer[] getPermIds() {
		return permIds;
	}

	public void setPermIds(Integer[] permIds) {
		this.permIds = permIds;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public Integer[] getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(Integer[] deptIds) {
		this.deptIds = deptIds;
	}

}
