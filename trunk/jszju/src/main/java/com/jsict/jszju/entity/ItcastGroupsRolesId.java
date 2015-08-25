package com.jsict.jszju.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ItcastGroupsRolesId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class ItcastGroupsRolesId implements java.io.Serializable {

	// Fields

	private Integer groupId;
	private Integer elt;

	// Constructors

	/** default constructor */
	public ItcastGroupsRolesId() {
	}

	/** full constructor */
	public ItcastGroupsRolesId(Integer groupId, Integer elt) {
		this.groupId = groupId;
		this.elt = elt;
	}

	// Property accessors

	@Column(name = "groupId_", nullable = false)
	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Column(name = "elt", nullable = false)
	public Integer getElt() {
		return this.elt;
	}

	public void setElt(Integer elt) {
		this.elt = elt;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ItcastGroupsRolesId))
			return false;
		ItcastGroupsRolesId castOther = (ItcastGroupsRolesId) other;

		return ((this.getGroupId() == castOther.getGroupId()) || (this
				.getGroupId() != null
				&& castOther.getGroupId() != null && this.getGroupId().equals(
				castOther.getGroupId())))
				&& ((this.getElt() == castOther.getElt()) || (this.getElt() != null
						&& castOther.getElt() != null && this.getElt().equals(
						castOther.getElt())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGroupId() == null ? 0 : this.getGroupId().hashCode());
		result = 37 * result
				+ (getElt() == null ? 0 : this.getElt().hashCode());
		return result;
	}

}