package com.qcx.code.service;

import java.util.List;

import com.qcx.code.domain.auth.Role;
import com.qcx.common.exception.AuthException;
import com.qcx.core.web.mvc.JqGridPage;

public interface IRoleService {

	JqGridPage<Role> queryPage(Role role);

	List<Role> queryAll() throws AuthException;

	List<Role> queryByUser(String userId) throws AuthException;

	Role query(Integer code) throws AuthException;

	boolean add(Role role) throws AuthException;

	boolean update(Role role) throws AuthException;

	boolean delete(Integer code) throws AuthException;

}
