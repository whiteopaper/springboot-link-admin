package com.qcx.code.dao;

import com.qcx.code.domain.auth.Work;

import java.util.List;

public interface IWorkDao {

	List<Work> selectList(Work work);

	Work selectByUid(Work work);

	int insert(Work work);

	int update(Work work);
	
	int delete(Work work);

}
