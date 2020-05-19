package com.qcx.code.service.impl;

import com.qcx.code.dao.IResumeDao;
import com.qcx.code.domain.auth.Resume;
import com.qcx.code.service.IResumeService;
import com.qcx.common.GlobalUser;
import com.qcx.common.exception.AuthException;
import com.qcx.common.utils.StringUtils;
import com.qcx.common.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ResumeService implements IResumeService {

	@Autowired
	private IResumeDao resumeDao;

	@Override
	public Resume query(Resume resume) throws AuthException {
		return resumeDao.selectByUid(resume);
	}

	@Override
	public List<Resume> queryAll(Resume resume) throws AuthException {
		return resumeDao.selectList(resume);
	}

	void validate(Resume resume){
		if (StringUtils.isBlank(resume.getIntention_position())) {
			throw new AuthException("意向岗位不能为空");
		}
		if (StringUtils.isBlank(resume.getIntention_wages())) {
			throw new AuthException("意向工资不能为空");
		}
		if (StringUtils.isBlank(resume.getEducation_school_time())) {
			throw new AuthException("学校/时间不能为空");
		}
		if (StringUtils.isBlank(resume.getEducation_department_major())) {
			throw new AuthException("院系/专业不能为空");
		}
	}

	@Transactional(value = "baseTransactionManager")
	@Override
	public boolean add(Resume resume) throws AuthException {
		validate(resume);
		resume.setId(UUIDUtils.generateUUID());
		resume.setUser_id(GlobalUser.getUserInfo().getUid());
		resume.setCreate_time(new Date());
		resume.setVisit_num(0);
		int result = resumeDao.insert(resume);
		if (result < 0) {
			throw new AuthException("新增失败");
		}
		return true;
	}

	@Transactional(value = "baseTransactionManager")
	@Override
	public boolean update(Resume resume) throws AuthException {
		validate(resume);
		resume.setUser_id(GlobalUser.getUserInfo().getUid());
		int result = resumeDao.update(resume);
		if (result < 0) {
			throw new AuthException("保存失败");
		}
		return true;
	}

	@Transactional(value = "baseTransactionManager")
	@Override
	public boolean delete() throws AuthException {
		String code = GlobalUser.getUserInfo().getUid();
		if (code == null) {
			throw new AuthException("删除条件不能为空");
		}
		Resume resume = new Resume();
		resume.setUser_id(code);
		int result = resumeDao.delete(resume);
		if (result < 0) {
			throw new AuthException("删除失败");
		}
		return true;
	}
}
