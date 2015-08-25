package com.jsict.jszju.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ItcastSystemlog entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_systemlog")
public class ItcastSystemlog implements java.io.Serializable {

	// Fields

	private Integer id;
	private ItcastUser itcastUser;
	private Date logTime;
	private String comment;
	private String operIpAddr;
	private Set<ItcastOperationlog> itcastOperationlogs = new HashSet<ItcastOperationlog>(
			0);
	private Set<ItcastExceptionlog> itcastExceptionlogs = new HashSet<ItcastExceptionlog>(
			0);

	// Constructors

	/** default constructor */
	public ItcastSystemlog() {
	}

	/** full constructor */
	public ItcastSystemlog(ItcastUser itcastUser, Date logTime, String comment,
			String operIpAddr, Set<ItcastOperationlog> itcastOperationlogs,
			Set<ItcastExceptionlog> itcastExceptionlogs) {
		this.itcastUser = itcastUser;
		this.logTime = logTime;
		this.comment = comment;
		this.operIpAddr = operIpAddr;
		this.itcastOperationlogs = itcastOperationlogs;
		this.itcastExceptionlogs = itcastExceptionlogs;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id_", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operator_")
	public ItcastUser getItcastUser() {
		return this.itcastUser;
	}

	public void setItcastUser(ItcastUser itcastUser) {
		this.itcastUser = itcastUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "logTime_", length = 19)
	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	@Column(name = "comment_", length = 65535)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "operIpAddr_", length = 16)
	public String getOperIpAddr() {
		return this.operIpAddr;
	}

	public void setOperIpAddr(String operIpAddr) {
		this.operIpAddr = operIpAddr;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastSystemlog")
	public Set<ItcastOperationlog> getItcastOperationlogs() {
		return this.itcastOperationlogs;
	}

	public void setItcastOperationlogs(
			Set<ItcastOperationlog> itcastOperationlogs) {
		this.itcastOperationlogs = itcastOperationlogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastSystemlog")
	public Set<ItcastExceptionlog> getItcastExceptionlogs() {
		return this.itcastExceptionlogs;
	}

	public void setItcastExceptionlogs(
			Set<ItcastExceptionlog> itcastExceptionlogs) {
		this.itcastExceptionlogs = itcastExceptionlogs;
	}

}