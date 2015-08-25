package com.jsict.jszju.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ItcastExceptionlog entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_exceptionlog", catalog = "jszuaadb")
public class ItcastExceptionlog implements java.io.Serializable {

	// Fields

	private Integer exceptionLogId;
	private ItcastSystemlog itcastSystemlog;
	private String className;
	private String summary;
	private String detailMessage;

	// Constructors

	/** default constructor */
	public ItcastExceptionlog() {
	}

	/** minimal constructor */
	public ItcastExceptionlog(ItcastSystemlog itcastSystemlog) {
		this.itcastSystemlog = itcastSystemlog;
	}

	/** full constructor */
	public ItcastExceptionlog(ItcastSystemlog itcastSystemlog,
			String className, String summary, String detailMessage) {
		this.itcastSystemlog = itcastSystemlog;
		this.className = className;
		this.summary = summary;
		this.detailMessage = detailMessage;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "exceptionLogId_", unique = true, nullable = false)
	public Integer getExceptionLogId() {
		return this.exceptionLogId;
	}

	public void setExceptionLogId(Integer exceptionLogId) {
		this.exceptionLogId = exceptionLogId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exceptionLogId_", unique = true, nullable = false, insertable = false, updatable = false)
	public ItcastSystemlog getItcastSystemlog() {
		return this.itcastSystemlog;
	}

	public void setItcastSystemlog(ItcastSystemlog itcastSystemlog) {
		this.itcastSystemlog = itcastSystemlog;
	}

	@Column(name = "className_")
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "summary_", length = 65535)
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "detailMessage_", length = 65535)
	public String getDetailMessage() {
		return this.detailMessage;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}

}