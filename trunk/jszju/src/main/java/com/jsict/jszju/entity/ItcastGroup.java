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
 * ItcastGroup entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_group")
public class ItcastGroup implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String description;
	private Set<ItcastUsersgroups> itcastUsersgroupses = new HashSet<ItcastUsersgroups>(
			0);
	private Set<ItcastGroupsRoles> itcastGroupsRoleses = new HashSet<ItcastGroupsRoles>(
			0);

	// Constructors

	/** default constructor */
	public ItcastGroup() {
	}

	/** full constructor */
	public ItcastGroup(String name, String description,
			Set<ItcastUsersgroups> itcastUsersgroupses,
			Set<ItcastGroupsRoles> itcastGroupsRoleses) {
		this.name = name;
		this.description = description;
		this.itcastUsersgroupses = itcastUsersgroupses;
		this.itcastGroupsRoleses = itcastGroupsRoleses;
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

	@Column(name = "name_", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description_")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastGroup")
	public Set<ItcastUsersgroups> getItcastUsersgroupses() {
		return this.itcastUsersgroupses;
	}

	public void setItcastUsersgroupses(
			Set<ItcastUsersgroups> itcastUsersgroupses) {
		this.itcastUsersgroupses = itcastUsersgroupses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastGroup")
	public Set<ItcastGroupsRoles> getItcastGroupsRoleses() {
		return this.itcastGroupsRoleses;
	}

	public void setItcastGroupsRoleses(
			Set<ItcastGroupsRoles> itcastGroupsRoleses) {
		this.itcastGroupsRoleses = itcastGroupsRoleses;
	}

}