package com.qcx.code.dao;

import com.qcx.code.domain.logs.BLog;
import com.qcx.code.domain.logs.BLogVO;
import com.qcx.core.web.mvc.JqGridPage;

public interface ILogDao {

	JqGridPage<BLog> selectPage(BLogVO  log);

	int insert(BLog log);

}
