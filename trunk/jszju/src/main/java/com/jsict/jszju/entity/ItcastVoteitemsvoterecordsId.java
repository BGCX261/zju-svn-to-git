package com.jsict.jszju.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ItcastVoteitemsvoterecordsId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class ItcastVoteitemsvoterecordsId implements java.io.Serializable {

	// Fields

	private Integer voteRecordId;
	private Integer voteItemId;

	// Constructors

	/** default constructor */
	public ItcastVoteitemsvoterecordsId() {
	}

	/** full constructor */
	public ItcastVoteitemsvoterecordsId(Integer voteRecordId, Integer voteItemId) {
		this.voteRecordId = voteRecordId;
		this.voteItemId = voteItemId;
	}

	// Property accessors

	@Column(name = "voteRecordId_", nullable = false)
	public Integer getVoteRecordId() {
		return this.voteRecordId;
	}

	public void setVoteRecordId(Integer voteRecordId) {
		this.voteRecordId = voteRecordId;
	}

	@Column(name = "voteItemId_", nullable = false)
	public Integer getVoteItemId() {
		return this.voteItemId;
	}

	public void setVoteItemId(Integer voteItemId) {
		this.voteItemId = voteItemId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ItcastVoteitemsvoterecordsId))
			return false;
		ItcastVoteitemsvoterecordsId castOther = (ItcastVoteitemsvoterecordsId) other;

		return ((this.getVoteRecordId() == castOther.getVoteRecordId()) || (this
				.getVoteRecordId() != null
				&& castOther.getVoteRecordId() != null && this
				.getVoteRecordId().equals(castOther.getVoteRecordId())))
				&& ((this.getVoteItemId() == castOther.getVoteItemId()) || (this
						.getVoteItemId() != null
						&& castOther.getVoteItemId() != null && this
						.getVoteItemId().equals(castOther.getVoteItemId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getVoteRecordId() == null ? 0 : this.getVoteRecordId()
						.hashCode());
		result = 37
				* result
				+ (getVoteItemId() == null ? 0 : this.getVoteItemId()
						.hashCode());
		return result;
	}

}