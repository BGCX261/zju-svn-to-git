package com.jsict.jszju.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Schoolfellowinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "schoolfellowinfo")
public class Schoolfellowinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private String title;
	private String content;
	private String time;
	private String visittime;
	private String isview;

	// Constructors

	/** default constructor */
	public Schoolfellowinfo() {
	}

	/** minimal constructor */
	public Schoolfellowinfo(String title) {
		this.title = title;
	}

	/** full constructor */
	public Schoolfellowinfo(Integer userid, String title, String content,
			String time, String visittime, String isview) {
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.time = time;
		this.visittime = visittime;
		this.isview = isview;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userid")
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Column(name = "title", nullable = false)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "time", length = 20)
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "visittime", length = 20)
	public String getVisittime() {
		return this.visittime;
	}

	public void setVisittime(String visittime) {
		this.visittime = visittime;
	}

	@Column(name = "isview", length = 2)
	public String getIsview() {
		return this.isview;
	}

	public void setIsview(String isview) {
		this.isview = isview;
	}

}