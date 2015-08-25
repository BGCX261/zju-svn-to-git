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
 * ItcastVoteitemsvoterecords entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_voteitemsvoterecords")
public class ItcastVoteitemsvoterecords implements java.io.Serializable {

	// Fields

	private ItcastVoteitemsvoterecordsId id;
	private ItcastVoteitem itcastVoteitem;
	private ItcastVoterecord itcastVoterecord;

	// Constructors

	/** default constructor */
	public ItcastVoteitemsvoterecords() {
	}

	/** full constructor */
	public ItcastVoteitemsvoterecords(ItcastVoteitemsvoterecordsId id,
			ItcastVoteitem itcastVoteitem, ItcastVoterecord itcastVoterecord) {
		this.id = id;
		this.itcastVoteitem = itcastVoteitem;
		this.itcastVoterecord = itcastVoterecord;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "voteRecordId", column = @Column(name = "voteRecordId_", nullable = false)),
			@AttributeOverride(name = "voteItemId", column = @Column(name = "voteItemId_", nullable = false)) })
	public ItcastVoteitemsvoterecordsId getId() {
		return this.id;
	}

	public void setId(ItcastVoteitemsvoterecordsId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voteItemId_", nullable = false, insertable = false, updatable = false)
	public ItcastVoteitem getItcastVoteitem() {
		return this.itcastVoteitem;
	}

	public void setItcastVoteitem(ItcastVoteitem itcastVoteitem) {
		this.itcastVoteitem = itcastVoteitem;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voteRecordId_", nullable = false, insertable = false, updatable = false)
	public ItcastVoterecord getItcastVoterecord() {
		return this.itcastVoterecord;
	}

	public void setItcastVoterecord(ItcastVoterecord itcastVoterecord) {
		this.itcastVoterecord = itcastVoterecord;
	}

}