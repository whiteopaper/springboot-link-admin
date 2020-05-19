package com.qcx.code.service.impl;

import com.qcx.code.dao.IWorkDao;
import com.qcx.code.domain.auth.Work;
import com.qcx.code.service.IWorkService;
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
public class WorkService implements IWorkService {

	@Autowired
	private IWorkDao WorkDao;

	@Override
	public Work query(Work work) throws AuthException {
		return WorkDao.selectByUid(work);
	}

	@Override
	public List<Work> queryAll(Work work) throws AuthException {
		return WorkDao.selectList(work);
	}

	void validate(Work work){
//		if (StringUtils.isBlank(work.getIntention_position())) {
//			throw new AuthException("意向岗位不能为空");
//		}
//		if (StringUtils.isBlank(work.getIntention_wages())) {
//			throw new AuthException("意向工资不能为空");
//		}
//		if (StringUtils.isBlank(work.getEducation_school_time())) {
//			throw new AuthException("学校/时间不能为空");
//		}
//		if (StringUtils.isBlank(work.getEducation_department_major())) {
//			throw new AuthException("院系/专业不能为空");
//		}
	}

	@Transactional(value = "baseTransactionManager")
	@Override
	public boolean add(Work work) throws AuthException {
		validate(work);
		work.setId(UUIDUtils.generateUUID());
		work.setUser_id(GlobalUser.getUserInfo().getUid());
		work.setCreate_time(new Date());
		work.setVisit_num(0);
		int result = WorkDao.insert(work);
		if (result < 0) {
			throw new AuthException("新增失败");
		}
		return true;
	}

	@Transactional(value = "baseTransactionManager")
	@Override
	public boolean update(Work work) throws AuthException {
		validate(work);
		work.setUser_id(GlobalUser.getUserInfo().getUid());
		int result = WorkDao.update(work);
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
		Work work = new Work();
		work.setUser_id(code);
		int result = WorkDao.delete(work);
		if (result < 0) {
			throw new AuthException("删除失败");
		}
		return true;
	}
}
