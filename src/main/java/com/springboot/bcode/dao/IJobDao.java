package com.springboot.bcode.dao;

import java.util.List;

import com.springboot.bcode.domain.auth.Job;
import com.springboot.core.web.mvc.JqGridPage;

public interface IJobDao {

	JqGridPage<Job> selectPage(Job job);
	
	List<Job> selectAll();

	Job selectOne(Job job);

	Job selectOne(Integer id);

	int insert(Job job);

	int update(Job job);

	int updateState(Integer id, int state);

	int delete(Job job);

}
