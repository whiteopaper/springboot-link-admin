package com.qcx.code.dao;

import com.qcx.code.domain.auth.Resume;

import java.util.List;

public interface IResumeDao {

	List<Resume> selectList(Resume resume);

	Resume selectByUid(Resume resume);

	int insert(Resume resume);

	int update(Resume resume);
	
	int delete(Resume resume);

}
