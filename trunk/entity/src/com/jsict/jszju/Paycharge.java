package com.jsict.jszju;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Paycharge entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "paycharge", catalog = "cedatabase")
public class Paycharge implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String money;
	private String contribute;

	// Constructors

	/** default constructor */
	public Paycharge() {
	}

	/** minimal constructor */
	public Paycharge(String name, String money) {
		this.name = name;
		this.money = money;
	}

	/** full constructor */
	public Paycharge(String name, String money, String contribute) {
		this.name = name;
		this.money = money;
		this.contribute = contribute;
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

	@Column(name = "money", nullable = false, length = 30)
	public String getMoney() {
		return this.money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@Column(name = "contribute", length = 30)
	public String getContribute() {
		return this.contribute;
	}

	public void setContribute(String contribute) {
		this.contribute = contribute;
	}

}