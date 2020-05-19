package com.qcx.core.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import com.qcx.core.jdbc.page.ISQLPageHandle;
import com.qcx.core.jdbc.support.AbstractJdbcSupport;

/**
 * 
 * @ClassName: BaseDaoImpl
 *
 */
public class BaseDaoImpl extends AbstractJdbcSupport {
	@Autowired
	@Qualifier("baseJdbcTemplate")
	protected JdbcTemplate jdbcTemplate;

	/**
	 * 分页处理
	 */
	@Autowired
	@Qualifier("mysqlSQLPageHandle")
	protected ISQLPageHandle mysqlSQLPageHandle;

	@Override
	protected JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	protected ISQLPageHandle getSqlPageHandle() {
		return mysqlSQLPageHandle;
	}

}
