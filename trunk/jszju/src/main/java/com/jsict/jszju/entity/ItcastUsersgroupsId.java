package com.jsict.jszju.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ItcastUsersgroupsId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class ItcastUsersgroupsId implements java.io.Serializable {

	// Fields

	private Integer userId;
	private Integer groupId;

	// Constructors

	/** default constructor */
	public ItcastUsersgroupsId() {
	}

	/** full constructor */
	public ItcastUsersgroupsId(Integer userId, Integer groupId) {
		this.userId = userId;
		this.groupId = groupId;
	}

	// Property accessors

	@Column(name = "userId_", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "groupId_", nullable = false)
	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ItcastUsersgroupsId))
			return false;
		ItcastUsersgroupsId castOther = (ItcastUsersgroupsId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(
				castOther.getUserId())))
				&& ((this.getGroupId() == castOther.getGroupId()) || (this
						.getGroupId() != null
						&& castOther.getGroupId() != null && this.getGroupId()
						.equals(castOther.getGroupId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getGroupId() == null ? 0 : this.getGroupId().hashCode());
		return result;
	}

}