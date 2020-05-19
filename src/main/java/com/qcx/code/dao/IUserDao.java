package com.qcx.code.dao;

import java.util.List;

import com.qcx.code.domain.auth.UserInfo;
import com.qcx.code.domain.auth.UserRole;
import com.qcx.core.web.mvc.JqGridPage;

public interface IUserDao {

	JqGridPage<UserInfo> selectPage(UserInfo user);

	UserInfo find(UserInfo user);

	List<UserInfo> findList(UserInfo user);

	UserInfo select(String id);

	int insert(UserInfo user);

	int[] insert(List<UserRole> list);

	int update(UserInfo user);

	int updateState(String uid,int state);

	UserRole select(UserRole userRelationRole);

	int delete(UserInfo user);

	int delete(UserRole userRelationRole);

}
