package com.qcx.code.dao;

import java.util.List;

import com.qcx.code.domain.auth.Department;

public interface IDepartmentDao {

	Department selectOne(Integer id);

	List<Department> findChild(Integer parentId);

	List<Department> selectAll();

	int insert(Department dept);

	int update(Department dept);
	
	int delete(Department dept);

}
