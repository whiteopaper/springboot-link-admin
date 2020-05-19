package com.qcx.code.service;

import java.util.List;

import com.qcx.code.domain.auth.Permission;
import com.qcx.common.exception.AuthException;

public interface IPermissionService {

	List<Permission> queryAll();

	List<Permission> queryByRole(Integer[] roleIds);

	Permission query(Integer code) throws AuthException;

	boolean add(Permission right) throws AuthException;

	boolean update(Permission right) throws AuthException;

	boolean delete(Integer code) throws AuthException;

}
