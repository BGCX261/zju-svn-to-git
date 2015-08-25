package com.jsict.jszju.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ItcastConfig entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_config")
public class ItcastConfig implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String value;

	// Constructors

	/** default constructor */
	public ItcastConfig() {
	}

	/** full constructor */
	public ItcastConfig(String name, String value) {
		this.name = name;
		this.value = value;
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

	@Column(name = "value_")
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}