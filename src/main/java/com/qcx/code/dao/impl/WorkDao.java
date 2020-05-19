package com.qcx.code.dao.impl;

import com.qcx.code.dao.IWorkDao;
import com.qcx.code.domain.auth.Work;
import com.qcx.common.exception.AuthException;
import com.qcx.common.utils.BeanUtils;
import com.qcx.common.utils.StringUtils;
import com.qcx.core.jdbc.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkDao extends BaseDaoImpl implements IWorkDao {


	@Override
	public List<Work> selectList(Work work) {
		//数据
		List<Work> list = super.select(
				resumeListSql(work),
				null,
				Work.class
		);
		for (Work re : list){
			int visit_num = re.getVisit_num() + 1;
			Work res = new Work();
			BeanUtils.copyObject(res,re);
			res.setVisit_num(visit_num);
			int result = update(res);
			if (result < 0) {
				throw new AuthException("点击量增加失败");
			}
		}
		return list;
	}

	private String resumeListSql(Work work) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_web_resume");
		sql.append(" where 1=1");
		//专业模糊查询
//		if (StringUtils.isNotBlank(resume.getEducation_department_major())) {
//			sql.append(" and education_department_major like '%").append(
//					resume.getEducation_department_major().trim() + "%' ");
//		}
		//最新热门排序
		sql.append(" order by visit_num desc , create_time desc");
		return sql.toString();
	}

	@Override
	public Work selectByUid(Work work) {
		List<Work> list = super.select(work);
		if ( list != null ){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int insert(Work work) {
		return super.insert(work);
	}

	@Override
	public int update(Work work) {
		return super.update(work);
	}

	@Override
	public int delete(Work work) { return super.delete(work); }

}
