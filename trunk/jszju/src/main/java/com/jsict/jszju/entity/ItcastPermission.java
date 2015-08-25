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
 * ItcastPermission entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_permission")
public class ItcastPermission implements java.io.Serializable {

	// Fields

	private Integer id;
	private ItcastRole itcastRole;
	private ItcastPermissiongroup itcastPermissiongroup;
	private String name;
	private String actionName;
	private String resourceName;
	private Integer idx;

	// Constructors

	/** default constructor */
	public ItcastPermission() {
	}

	/** full constructor */
	public ItcastPermission(ItcastRole itcastRole,
			ItcastPermissiongroup itcastPermissiongroup, String name,
			String actionName, String resourceName, Integer idx) {
		this.itcastRole = itcastRole;
		this.itcastPermissiongroup = itcastPermissiongroup;
		this.name = name;
		this.actionName = actionName;
		this.resourceName = resourceName;
		this.idx = idx;
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
	@JoinColumn(name = "roleId_")
	public ItcastRole getItcastRole() {
		return this.itcastRole;
	}

	public void setItcastRole(ItcastRole itcastRole) {
		this.itcastRole = itcastRole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permissionGroupId_")
	public ItcastPermissiongroup getItcastPermissiongroup() {
		return this.itcastPermissiongroup;
	}

	public void setItcastPermissiongroup(
			ItcastPermissiongroup itcastPermissiongroup) {
		this.itcastPermissiongroup = itcastPermissiongroup;
	}

	@Column(name = "name_")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "actionName_", length = 64)
	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	@Column(name = "resourceName_", length = 64)
	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(name = "idx")
	public Integer getIdx() {
		return this.idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

}