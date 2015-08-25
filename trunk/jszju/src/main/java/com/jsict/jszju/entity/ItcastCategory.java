package com.jsict.jszju.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ItcastCategory entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_category")
public class ItcastCategory implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer order;
	private Set<ItcastForum> itcastForums = new HashSet<ItcastForum>(0);

	// Constructors

	/** default constructor */
	public ItcastCategory() {
	}

	/** full constructor */
	public ItcastCategory(String name, Integer order,
			Set<ItcastForum> itcastForums) {
		this.name = name;
		this.order = order;
		this.itcastForums = itcastForums;
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

	@Column(name = "name_")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "order_")
	public Integer getOrder() {
		return this.order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastCategory")
	public Set<ItcastForum> getItcastForums() {
		return this.itcastForums;
	}

	public void setItcastForums(Set<ItcastForum> itcastForums) {
		this.itcastForums = itcastForums;
	}

}