package com.jsict.jszju;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Adminrestinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "adminrestinfo")
public class Adminrestinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String password;
	private String newpassword1;
	private String newpassword2;
	private String realname;

	// Constructors

	/** default constructor */
	public Adminrestinfo() {
	}

	/** minimal constructor */
	public Adminrestinfo(String name, String password) {
		this.name = name;
		this.password = password;
	}

	/** full constructor */
	public Adminrestinfo(String name, String password, String newpassword1,
			String newpassword2, String realname) {
		this.name = name;
		this.password = password;
		this.newpassword1 = newpassword1;
		this.newpassword2 = newpassword2;
		this.realname = realname;
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

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "password", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "newpassword1", length = 20)
	public String getNewpassword1() {
		return this.newpassword1;
	}

	public void setNewpassword1(String newpassword1) {
		this.newpassword1 = newpassword1;
	}

	@Column(name = "newpassword2")
	public String getNewpassword2() {
		return this.newpassword2;
	}

	public void setNewpassword2(String newpassword2) {
		this.newpassword2 = newpassword2;
	}

	@Column(name = "realname", length = 12)
	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

}