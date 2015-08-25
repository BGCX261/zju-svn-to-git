package com.jsict.jszju.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ItcastGroupsRoles entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_groups_roles")
public class ItcastGroupsRoles implements java.io.Serializable {

	// Fields

	private ItcastGroupsRolesId id;
	private ItcastGroup itcastGroup;
	private ItcastRole itcastRole;

	// Constructors

	/** default constructor */
	public ItcastGroupsRoles() {
	}

	/** full constructor */
	public ItcastGroupsRoles(ItcastGroupsRolesId id, ItcastGroup itcastGroup,
			ItcastRole itcastRole) {
		this.id = id;
		this.itcastGroup = itcastGroup;
		this.itcastRole = itcastRole;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "groupId", column = @Column(name = "groupId_", nullable = false)),
			@AttributeOverride(name = "elt", column = @Column(name = "elt", nullable = false)) })
	public ItcastGroupsRolesId getId() {
		return this.id;
	}

	public void setId(ItcastGroupsRolesId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "groupId_", nullable = false, insertable = false, updatable = false)
	public ItcastGroup getItcastGroup() {
		return this.itcastGroup;
	}

	public void setItcastGroup(ItcastGroup itcastGroup) {
		this.itcastGroup = itcastGroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "elt", nullable = false, insertable = false, updatable = false)
	public ItcastRole getItcastRole() {
		return this.itcastRole;
	}

	public void setItcastRole(ItcastRole itcastRole) {
		this.itcastRole = itcastRole;
	}

}