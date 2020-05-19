package com.qcx.code.service;

import com.qcx.code.domain.auth.Work;
import com.qcx.common.exception.AuthException;

import java.util.List;

public interface IWorkService {

	List<Work> queryAll(Work work) throws AuthException;

	Work query(Work work) throws AuthException;

	boolean add(Work work) throws AuthException;

	boolean update(Work work) throws AuthException;

	boolean delete() throws AuthException;

}
