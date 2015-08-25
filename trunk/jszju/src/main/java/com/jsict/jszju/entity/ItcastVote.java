package com.jsict.jszju.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ItcastVote entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_vote")
public class ItcastVote implements java.io.Serializable {

	// Fields

	private Integer id;
	private ItcastTopic itcastTopic;
	private String title;
	private Date creationTime;
	private Date closeTime;
	private String multiple;
	private Integer result;
	private Integer idx;
	private Set<ItcastVoteitem> itcastVoteitems = new HashSet<ItcastVoteitem>(0);

	// Constructors

	/** default constructor */
	public ItcastVote() {
	}

	/** full constructor */
	public ItcastVote(ItcastTopic itcastTopic, String title, Date creationTime,
			Date closeTime, String multiple, Integer result, Integer idx,
			Set<ItcastVoteitem> itcastVoteitems) {
		this.itcastTopic = itcastTopic;
		this.title = title;
		this.creationTime = creationTime;
		this.closeTime = closeTime;
		this.multiple = multiple;
		this.result = result;
		this.idx = idx;
		this.itcastVoteitems = itcastVoteitems;
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
	@JoinColumn(name = "topicId_")
	public ItcastTopic getItcastTopic() {
		return this.itcastTopic;
	}

	public void setItcastTopic(ItcastTopic itcastTopic) {
		this.itcastTopic = itcastTopic;
	}

	@Column(name = "title_")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creationTime_", length = 19)
	public Date getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "closeTime_", length = 19)
	public Date getCloseTime() {
		return this.closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	@Column(name = "multiple_")
	public String getMultiple() {
		return this.multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastVote")
	public Set<ItcastVoteitem> getItcastVoteitems() {
		return this.itcastVoteitems;
	}

	public void setItcastVoteitems(Set<ItcastVoteitem> itcastVoteitems) {
		this.itcastVoteitems = itcastVoteitems;
	}

}