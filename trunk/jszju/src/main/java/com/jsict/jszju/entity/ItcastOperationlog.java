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
 * ItcastOperationlog entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_operationlog")
public class ItcastOperationlog implements java.io.Serializable {

	// Fields

	private Integer operationLogId;
	private ItcastSystemlog itcastSystemlog;
	private String entityType;
	private Integer entityId;
	private String typeName;

	// Constructors

	/** default constructor */
	public ItcastOperationlog() {
	}

	/** minimal constructor */
	public ItcastOperationlog(ItcastSystemlog itcastSystemlog) {
		this.itcastSystemlog = itcastSystemlog;
	}

	/** full constructor */
	public ItcastOperationlog(ItcastSystemlog itcastSystemlog,
			String entityType, Integer entityId, String typeName) {
		this.itcastSystemlog = itcastSystemlog;
		this.entityType = entityType;
		this.entityId = entityId;
		this.typeName = typeName;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "operationLogId_", unique = true, nullable = false)
	public Integer getOperationLogId() {
		return this.operationLogId;
	}

	public void setOperationLogId(Integer operationLogId) {
		this.operationLogId = operationLogId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operationLogId_", unique = true, nullable = false, insertable = false, updatable = false)
	public ItcastSystemlog getItcastSystemlog() {
		return this.itcastSystemlog;
	}

	public void setItcastSystemlog(ItcastSystemlog itcastSystemlog) {
		this.itcastSystemlog = itcastSystemlog;
	}

	@Column(name = "entityType_")
	public String getEntityType() {
		return this.entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	@Column(name = "entityId_")
	public Integer getEntityId() {
		return this.entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	@Column(name = "typeName_")
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}