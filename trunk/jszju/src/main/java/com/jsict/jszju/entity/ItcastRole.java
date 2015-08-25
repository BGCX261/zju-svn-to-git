package com.jsict.jszju.entity;

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

/**
 * ItcastRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_role")
public class ItcastRole implements java.io.Serializable {

	// Fields

	private Integer id;
	private ItcastRole itcastRole;
	private String name;
	private Set<ItcastRole> itcastRoles = new HashSet<ItcastRole>(0);
	private Set<ItcastGroupsRoles> itcastGroupsRoleses = new HashSet<ItcastGroupsRoles>(
			0);
	private Set<ItcastPermission> itcastPermissions = new HashSet<ItcastPermission>(
			0);

	// Constructors

	/** default constructor */
	public ItcastRole() {
	}

	/** full constructor */
	public ItcastRole(ItcastRole itcastRole, String name,
			Set<ItcastRole> itcastRoles,
			Set<ItcastGroupsRoles> itcastGroupsRoleses,
			Set<ItcastPermission> itcastPermissions) {
		this.itcastRole = itcastRole;
		this.name = name;
		this.itcastRoles = itcastRoles;
		this.itcastGroupsRoleses = itcastGroupsRoleses;
		this.itcastPermissions = itcastPermissions;
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
	@JoinColumn(name = "parentId_")
	public ItcastRole getItcastRole() {
		return this.itcastRole;
	}

	public void setItcastRole(ItcastRole itcastRole) {
		this.itcastRole = itcastRole;
	}

	@Column(name = "name_")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastRole")
	public Set<ItcastRole> getItcastRoles() {
		return this.itcastRoles;
	}

	public void setItcastRoles(Set<ItcastRole> itcastRoles) {
		this.itcastRoles = itcastRoles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastRole")
	public Set<ItcastGroupsRoles> getItcastGroupsRoleses() {
		return this.itcastGroupsRoleses;
	}

	public void setItcastGroupsRoleses(
			Set<ItcastGroupsRoles> itcastGroupsRoleses) {
		this.itcastGroupsRoleses = itcastGroupsRoleses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastRole")
	public Set<ItcastPermission> getItcastPermissions() {
		return this.itcastPermissions;
	}

	public void setItcastPermissions(Set<ItcastPermission> itcastPermissions) {
		this.itcastPermissions = itcastPermissions;
	}

}