package com.jsict.jszju;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Permission entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "permission")
public class Permission implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private String description;
	private String type;
	private String url;
	private String itemId;
	private Integer version;
	private String parentId;
	private String name;
	private String platform;

	// Constructors

	/** default constructor */
	public Permission() {
	}

	/** minimal constructor */
	public Permission(String code, String type, String name, String platform) {
		this.code = code;
		this.type = type;
		this.name = name;
		this.platform = platform;
	}

	/** full constructor */
	public Permission(String code, String description, String type, String url,
			String itemId, Integer version, String parentId, String name,
			String platform) {
		this.code = code;
		this.description = description;
		this.type = type;
		this.url = url;
		this.itemId = itemId;
		this.version = version;
		this.parentId = parentId;
		this.name = name;
		this.platform = platform;
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

	@Column(name = "code", nullable = false, length = 6)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "description", length = 2000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "type", nullable = false, length = 6)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "url", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "item_id", length = 50)
	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "parent_id", length = 6)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "platform", nullable = false, length = 6)
	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

}