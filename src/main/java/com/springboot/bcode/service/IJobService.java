package com.springboot.bcode.service;

import java.util.List;

import com.springboot.bcode.domain.auth.Job;
import com.springboot.core.web.mvc.JqGridPage;

public interface IJobService {

	JqGridPage<Job> queryPage(Job job);
	
	List<Job> queryAll();

	Job find(Integer id);

	Job find(Job job);

	boolean add(Job job);

	boolean update(Job job);

	boolean delete(Integer id);

	boolean updateState(Job job);

}
