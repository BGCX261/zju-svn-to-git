package com.jsict.jszju.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ItcastPermissiongroup entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_permissiongroup")
public class ItcastPermissiongroup implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Set<ItcastPermission> itcastPermissions = new HashSet<ItcastPermission>(
			0);

	// Constructors

	/** default constructor */
	public ItcastPermissiongroup() {
	}

	/** full constructor */
	public ItcastPermissiongroup(String name,
			Set<ItcastPermission> itcastPermissions) {
		this.name = name;
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

	@Column(name = "name_")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastPermissiongroup")
	public Set<ItcastPermission> getItcastPermissions() {
		return this.itcastPermissions;
	}

	public void setItcastPermissions(Set<ItcastPermission> itcastPermissions) {
		this.itcastPermissions = itcastPermissions;
	}

}