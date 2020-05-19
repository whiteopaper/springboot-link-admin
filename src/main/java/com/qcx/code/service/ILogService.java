package com.qcx.code.service;

import com.qcx.code.domain.logs.BLog;
import com.qcx.code.domain.logs.BLogVO;
import com.qcx.core.web.mvc.JqGridPage;



public interface ILogService {

	JqGridPage<BLog> queryPage(BLogVO vo);
}
