package com.jsict.jszju;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Schoolfellownotes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "schoolfellownotes")
public class Schoolfellownotes implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String content;
	private String time;
	private String isview;
	private String visittime;

	// Constructors

	/** default constructor */
	public Schoolfellownotes() {
	}

	/** minimal constructor */
	public Schoolfellownotes(String title, String content) {
		this.title = title;
		this.content = content;
	}

	/** full constructor */
	public Schoolfellownotes(String title, String content, String time,
			String isview, String visittime) {
		this.title = title;
		this.content = content;
		this.time = time;
		this.isview = isview;
		this.visittime = visittime;
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

	@Column(name = "title", nullable = false)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", nullable = false, length = 65535)
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

	@Column(name = "isview", length = 2)
	public String getIsview() {
		return this.isview;
	}

	public void setIsview(String isview) {
		this.isview = isview;
	}

	@Column(name = "visittime", length = 10)
	public String getVisittime() {
		return this.visittime;
	}

	public void setVisittime(String visittime) {
		this.visittime = visittime;
	}

}