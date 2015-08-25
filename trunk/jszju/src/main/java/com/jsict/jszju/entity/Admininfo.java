package com.jsict.jszju.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Admininfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "admininfo")
public class Admininfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String password;
	private String role;
	private String isable;

	// Constructors

	/** default constructor */
	public Admininfo() {
	}

	/** full constructor */
	public Admininfo(String name, String password, String role) {
		this.name = name;
		this.password = password;
		this.role = role;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "password", length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "role", length = 30)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "isable", length = 30)
	public String getIsable() {
		return isable;
	}

	public void setIsable(String isable) {
		this.isable = isable;
	}

}