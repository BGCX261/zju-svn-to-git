package com.jsict.jszju.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Course entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "course")
public class Course implements java.io.Serializable {

	// Fields

	private String courseid;
	private String cname;
	private String cteacher;
	private String classplace;

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** full constructor */
	public Course(String cname, String cteacher, String classplace) {
		this.cname = cname;
		this.cteacher = cteacher;
		this.classplace = classplace;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "courseid", unique = true, nullable = false, length = 20)
	public String getCourseid() {
		return this.courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	@Column(name = "cname", length = 20)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "cteacher", length = 20)
	public String getCteacher() {
		return this.cteacher;
	}

	public void setCteacher(String cteacher) {
		this.cteacher = cteacher;
	}

	@Column(name = "classplace", length = 40)
	public String getClassplace() {
		return this.classplace;
	}

	public void setClassplace(String classplace) {
		this.classplace = classplace;
	}

}