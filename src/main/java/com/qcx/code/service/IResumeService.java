package com.qcx.code.service;

import com.qcx.code.domain.auth.Resume;
import com.qcx.common.exception.AuthException;

import java.util.List;

public interface IResumeService {

	List<Resume> queryAll(Resume resume) throws AuthException;

	Resume query(Resume resume) throws AuthException;

	boolean add(Resume resume) throws AuthException;

	boolean update(Resume resume) throws AuthException;

	boolean delete() throws AuthException;

}
