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
 * ItcastUsersgroups entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_usersgroups")
public class ItcastUsersgroups implements java.io.Serializable {

	// Fields

	private ItcastUsersgroupsId id;
	private ItcastGroup itcastGroup;
	private ItcastUser itcastUser;

	// Constructors

	/** default constructor */
	public ItcastUsersgroups() {
	}

	/** full constructor */
	public ItcastUsersgroups(ItcastUsersgroupsId id, ItcastGroup itcastGroup,
			ItcastUser itcastUser) {
		this.id = id;
		this.itcastGroup = itcastGroup;
		this.itcastUser = itcastUser;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "userId", column = @Column(name = "userId_", nullable = false)),
			@AttributeOverride(name = "groupId", column = @Column(name = "groupId_", nullable = false)) })
	public ItcastUsersgroupsId getId() {
		return this.id;
	}

	public void setId(ItcastUsersgroupsId id) {
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
	@JoinColumn(name = "userId_", nullable = false, insertable = false, updatable = false)
	public ItcastUser getItcastUser() {
		return this.itcastUser;
	}

	public void setItcastUser(ItcastUser itcastUser) {
		this.itcastUser = itcastUser;
	}

}