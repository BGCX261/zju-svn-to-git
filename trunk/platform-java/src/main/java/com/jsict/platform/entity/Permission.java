package com.jsict.platform.entity;

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
import javax.persistence.Version;

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
    private Permission parentPermission;

    private Set<Permission> childPermissions = new HashSet<Permission>(0);

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
	
    @Version
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    public Permission getParentPermission()
    {
        return parentPermission;
    }

    public void setParentPermission(Permission parentPermission)
    {
        this.parentPermission = parentPermission;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentPermission")
    public Set<Permission> getChildPermissions()
    {
        return childPermissions;
    }

    public void setChildPermissions(Set<Permission> childrenPermissions)
    {
        this.childPermissions = childrenPermissions;
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        final Permission other = (Permission) obj;
        if(id == null)
        {
            if(other.id != null)
                return false;
        }
        else if(!id.equals(other.id))
            return false;
        return true;
    }
}