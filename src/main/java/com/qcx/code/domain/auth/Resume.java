package com.qcx.code.domain.auth;

import com.qcx.core.jdbc.annotation.Columns;
import com.qcx.core.jdbc.annotation.Tables;

import java.util.Date;


@Tables(table = "t_web_resume")
public class Resume {

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
	@Columns(column = "intention_position")
	private String intention_position;
	@Columns(column = "intention_city")
	private String intention_city;
	@Columns(column = "intention_wages")
	private String intention_wages;
	@Columns(column = "education_school_time")
	private String education_school_time;
	@Columns(column = "education_department_major")
	private String education_department_major;
	@Columns(column = "internship_company_time")
	private String internship_company_time;
	@Columns(column = "internship_position")
	private String internship_position;
	@Columns(column = "internship_describe")
	private String internship_describe;
	@Columns(column = "experience_title")
	private String experience_title;
	@Columns(column = "experience_position")
	private String experience_position;
	@Columns(column = "experience_describe")
	private String experience_describe;
	@Columns(column = "honor_describe")
	private String honor_describe;
	@Columns(column = "self_comment")
	private String self_comment;
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

	public String getIntention_position() {
		return intention_position;
	}

	public void setIntention_position(String intention_position) {
		this.intention_position = intention_position;
	}

	public String getIntention_city() {
		return intention_city;
	}

	public void setIntention_city(String intention_city) {
		this.intention_city = intention_city;
	}

	public String getIntention_wages() {
		return intention_wages;
	}

	public void setIntention_wages(String intention_wages) {
		this.intention_wages = intention_wages;
	}

	public String getEducation_school_time() {
		return education_school_time;
	}

	public void setEducation_school_time(String education_school_time) {
		this.education_school_time = education_school_time;
	}

	public String getEducation_department_major() {
		return education_department_major;
	}

	public void setEducation_department_major(String education_department_major) {
		this.education_department_major = education_department_major;
	}

	public String getInternship_company_time() {
		return internship_company_time;
	}

	public void setInternship_company_time(String internship_company_time) {
		this.internship_company_time = internship_company_time;
	}

	public String getInternship_position() {
		return internship_position;
	}

	public void setInternship_position(String internship_position) {
		this.internship_position = internship_position;
	}

	public String getInternship_describe() {
		return internship_describe;
	}

	public void setInternship_describe(String internship_describe) {
		this.internship_describe = internship_describe;
	}

	public String getExperience_title() {
		return experience_title;
	}

	public void setExperience_title(String experience_title) {
		this.experience_title = experience_title;
	}

	public String getExperience_position() {
		return experience_position;
	}

	public void setExperience_position(String experience_position) {
		this.experience_position = experience_position;
	}

	public String getExperience_describe() {
		return experience_describe;
	}

	public void setExperience_describe(String experience_describe) {
		this.experience_describe = experience_describe;
	}

	public String getHonor_describe() {
		return honor_describe;
	}

	public void setHonor_describe(String honor_describe) {
		this.honor_describe = honor_describe;
	}

	public String getSelf_comment() {
		return self_comment;
	}

	public void setSelf_comment(String self_comment) {
		this.self_comment = self_comment;
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