package com.qcx.code.dao.impl;

import com.qcx.code.dao.IResumeDao;
import com.qcx.code.domain.auth.Resume;
import com.qcx.common.exception.AuthException;
import com.qcx.common.utils.BeanUtils;
import com.qcx.common.utils.StringUtils;
import com.qcx.core.jdbc.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResumeDao extends BaseDaoImpl implements IResumeDao {


	@Override
	public List<Resume> selectList(Resume resume) {
		//数据
		List<Resume> list = super.select(
				resumeListSql(resume),
				null,
				Resume.class
		);
		for (Resume re : list){
			int visit_num = re.getVisit_num() + 1;
			Resume res = new Resume();
			BeanUtils.copyObject(res,re);
			res.setVisit_num(visit_num);
			int result = update(res);
			if (result < 0) {
				throw new AuthException("点击量增加失败");
			}
		}
		return list;
	}

	private String resumeListSql(Resume resume) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_web_resume");
		sql.append(" where 1=1");
		//专业模糊查询
		if (StringUtils.isNotBlank(resume.getEducation_department_major())) {
			sql.append(" and education_department_major like '%").append(
					resume.getEducation_department_major().trim() + "%' ");
		}
		//意向岗位模糊查询
		if (StringUtils.isNotBlank(resume.getIntention_position())) {
			sql.append(" and intention_position like '%").append(
					resume.getIntention_position().trim() + "%' ");
		}
		//最新热门排序
		sql.append(" order by visit_num desc , create_time desc");
		return sql.toString();
	}

	@Override
	public Resume selectByUid(Resume resume) {
		List<Resume> list = super.select(resume);
		if ( list != null ){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int insert(Resume resume) {
		return super.insert(resume);
	}

	@Override
	public int update(Resume resume) {
		return super.update(resume);
	}

	@Override
	public int delete(Resume resume) { return super.delete(resume); }

}
