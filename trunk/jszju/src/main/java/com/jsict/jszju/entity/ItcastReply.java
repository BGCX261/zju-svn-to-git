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
 * ItcastReply entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_reply")
public class ItcastReply implements java.io.Serializable {

	// Fields

	private Integer id;
	private ItcastArticle itcastArticle;
	private ItcastTopic itcastTopic;
	private Integer floor;
	private Set<ItcastTopic> itcastTopics = new HashSet<ItcastTopic>(0);

	// Constructors

	/** default constructor */
	public ItcastReply() {
	}

	/** minimal constructor */
	public ItcastReply(ItcastArticle itcastArticle) {
		this.itcastArticle = itcastArticle;
	}

	/** full constructor */
	public ItcastReply(ItcastArticle itcastArticle, ItcastTopic itcastTopic,
			Integer floor, Set<ItcastTopic> itcastTopics) {
		this.itcastArticle = itcastArticle;
		this.itcastTopic = itcastTopic;
		this.floor = floor;
		this.itcastTopics = itcastTopics;
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
	@JoinColumn(name = "id_", unique = true, nullable = false, insertable = false, updatable = false)
	public ItcastArticle getItcastArticle() {
		return this.itcastArticle;
	}

	public void setItcastArticle(ItcastArticle itcastArticle) {
		this.itcastArticle = itcastArticle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "topicId_")
	public ItcastTopic getItcastTopic() {
		return this.itcastTopic;
	}

	public void setItcastTopic(ItcastTopic itcastTopic) {
		this.itcastTopic = itcastTopic;
	}

	@Column(name = "floor_")
	public Integer getFloor() {
		return this.floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastReply")
	public Set<ItcastTopic> getItcastTopics() {
		return this.itcastTopics;
	}

	public void setItcastTopics(Set<ItcastTopic> itcastTopics) {
		this.itcastTopics = itcastTopics;
	}

}