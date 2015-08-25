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
 * ItcastVoteitem entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_voteitem")
public class ItcastVoteitem implements java.io.Serializable {

	// Fields

	private Integer id;
	private ItcastVote itcastVote;
	private String content;
	private Integer result;
	private Integer idx;
	private Set<ItcastVoteitemsvoterecords> itcastVoteitemsvoterecordses = new HashSet<ItcastVoteitemsvoterecords>(
			0);

	// Constructors

	/** default constructor */
	public ItcastVoteitem() {
	}

	/** full constructor */
	public ItcastVoteitem(ItcastVote itcastVote, String content,
			Integer result, Integer idx,
			Set<ItcastVoteitemsvoterecords> itcastVoteitemsvoterecordses) {
		this.itcastVote = itcastVote;
		this.content = content;
		this.result = result;
		this.idx = idx;
		this.itcastVoteitemsvoterecordses = itcastVoteitemsvoterecordses;
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
	@JoinColumn(name = "voteId_")
	public ItcastVote getItcastVote() {
		return this.itcastVote;
	}

	public void setItcastVote(ItcastVote itcastVote) {
		this.itcastVote = itcastVote;
	}

	@Column(name = "content_")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "result_")
	public Integer getResult() {
		return this.result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	@Column(name = "idx_")
	public Integer getIdx() {
		return this.idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastVoteitem")
	public Set<ItcastVoteitemsvoterecords> getItcastVoteitemsvoterecordses() {
		return this.itcastVoteitemsvoterecordses;
	}

	public void setItcastVoteitemsvoterecordses(
			Set<ItcastVoteitemsvoterecords> itcastVoteitemsvoterecordses) {
		this.itcastVoteitemsvoterecordses = itcastVoteitemsvoterecordses;
	}

}