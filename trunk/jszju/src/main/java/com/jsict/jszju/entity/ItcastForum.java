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
import javax.persistence.UniqueConstraint;

/**
 * ItcastForum entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_forum", uniqueConstraints = @UniqueConstraint(columnNames = "lastTopicId_"))
public class ItcastForum implements java.io.Serializable {

	// Fields

	private Integer id;
	private ItcastTopic itcastTopic;
	private ItcastCategory itcastCategory;
	private String name;
	private String description;
	private Integer order;
	private Integer topicCount;
	private Integer articleCount;
	private Set<ItcastTopic> itcastTopics = new HashSet<ItcastTopic>(0);

	// Constructors

	/** default constructor */
	public ItcastForum() {
	}

	/** full constructor */
	public ItcastForum(ItcastTopic itcastTopic, ItcastCategory itcastCategory,
			String name, String description, Integer order, Integer topicCount,
			Integer articleCount, Set<ItcastTopic> itcastTopics) {
		this.itcastTopic = itcastTopic;
		this.itcastCategory = itcastCategory;
		this.name = name;
		this.description = description;
		this.order = order;
		this.topicCount = topicCount;
		this.articleCount = articleCount;
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
	@JoinColumn(name = "lastTopicId_", unique = true)
	public ItcastTopic getItcastTopic() {
		return this.itcastTopic;
	}

	public void setItcastTopic(ItcastTopic itcastTopic) {
		this.itcastTopic = itcastTopic;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId_")
	public ItcastCategory getItcastCategory() {
		return this.itcastCategory;
	}

	public void setItcastCategory(ItcastCategory itcastCategory) {
		this.itcastCategory = itcastCategory;
	}

	@Column(name = "name_")
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

	@Column(name = "order_")
	public Integer getOrder() {
		return this.order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Column(name = "topicCount_")
	public Integer getTopicCount() {
		return this.topicCount;
	}

	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}

	@Column(name = "articleCount_")
	public Integer getArticleCount() {
		return this.articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastForum")
	public Set<ItcastTopic> getItcastTopics() {
		return this.itcastTopics;
	}

	public void setItcastTopics(Set<ItcastTopic> itcastTopics) {
		this.itcastTopics = itcastTopics;
	}

}