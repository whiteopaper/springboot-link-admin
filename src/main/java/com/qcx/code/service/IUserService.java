package com.qcx.code.service;

import com.qcx.code.domain.auth.*;
import com.qcx.core.web.mvc.JqGridPage;

public interface IUserService {

	JqGridPage<UserInfo> queryPage(UserInfo user);

	String login(LoginVO vo);

	UserInfo info();

	boolean register(UserInfoVO vo);

	UserInfo find(String uid);

	UserInfo find(UserInfo user);

	void modifyPwd(ModifyPwdVO vo);

	boolean add(UserInfoVO vo);

	boolean update(UserInfoVO vo ,boolean type);

	boolean delete(String uid);
	
	boolean updateState(UserInfoVO vo);

	boolean modifyPersonalInfo(UserInfoVO user);

	boolean isRole(int rid);
}
