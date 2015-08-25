package com.jsict.jszju;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Schoolfellowhelp entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "schoolfellowhelp")
public class Schoolfellowhelp implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private String title;
	private String content;
	private String time;
	private String visittimes;
	private String isview;

	// Constructors

	/** default constructor */
	public Schoolfellowhelp() {
	}

	/** minimal constructor */
	public Schoolfellowhelp(String title) {
		this.title = title;
	}

	/** full constructor */
	public Schoolfellowhelp(Integer userid, String title, String content,
			String time, String visittimes, String isview) {
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.time = time;
		this.visittimes = visittimes;
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

	@Column(name = "visittimes", length = 20)
	public String getVisittimes() {
		return this.visittimes;
	}

	public void setVisittimes(String visittimes) {
		this.visittimes = visittimes;
	}

	@Column(name = "isview", length = 2)
	public String getIsview() {
		return this.isview;
	}

	public void setIsview(String isview) {
		this.isview = isview;
	}

}