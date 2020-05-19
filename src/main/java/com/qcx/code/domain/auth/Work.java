package com.qcx.code.domain.auth;

import com.qcx.core.jdbc.annotation.Columns;
import com.qcx.core.jdbc.annotation.Tables;

import java.util.Date;


@Tables(table = "t_web_work")
public class Work {

	// Fields
	@Columns(column = "id", primaryKey = true, autoIncrement = false)
	private String id;
	@Columns(column = "user_id")
	private String user_id;
	@Columns(column = "vsername")
	private String vsername;
	@Columns(column = "mobile")
	private String mobile;
	@Columns(column = "email")
	private String email;
	@Columns(column = "position")
	private String position;
	@Columns(column = "address")
	private String address;
	@Columns(column = "wages")
	private String wages;
	@Columns(column = "require")
	private String require;
	@Columns(column = "introduction")
	private String introduction;
	@Columns(column = "create_time")
	private Date create_time;
	@Columns(column = "visit_num")
	private Integer visit_num;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getVsername() {
		return vsername;
	}

	public void setVsername(String vsername) {
		this.vsername = vsername;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWages() {
		return wages;
	}

	public void setWages(String wages) {
		this.wages = wages;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getVisit_num() {
		return visit_num;
	}

	public void setVisit_num(Integer visit_num) {
		this.visit_num = visit_num;
	}
}