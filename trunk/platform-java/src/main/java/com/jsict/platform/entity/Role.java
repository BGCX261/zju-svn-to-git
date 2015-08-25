package com.jsict.platform.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Role entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ROLE")
public class Role implements java.io.Serializable
{

    // Fields
    private Long id;

    private Long version;

    private String name;

    private String description;

    private Date createdDate;

    //the code for the java side
    private String bCode;

    private Set<UserRole> userRoles = new HashSet<UserRole>(0);

    private Set<AssignableRole> assignablePermissionsForAssignableRoleId = new HashSet<AssignableRole>(
        0);

    private Set<RolePermission> rolePermissions = new HashSet<RolePermission>(0);

    private Set<AssignableRole> assignablePermissionsForRoleId = new HashSet<AssignableRole>(
        0);

    // Constructors

    /** default constructor */
    public Role()
    {
    }

    // Property accessors
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Version
    @Column(name = "VERSION", precision = 22, scale = 0)
    public Long getVersion()
    {
        return this.version;
    }

    public void setVersion(Long version)
    {
        this.version = version;
    }

    @Column(name = "DESCRIPTION", length = 2000)
    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", length = 7)
    public Date getCreatedDate()
    {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roleByAssignableRoleId")
    public Set<AssignableRole> getAssignablePermissionsForAssignableRoleId()
    {
        return this.assignablePermissionsForAssignableRoleId;
    }

    public void setAssignablePermissionsForAssignableRoleId(
            Set<AssignableRole> assignablePermissionsForAssignableRoleId)
    {
        this.assignablePermissionsForAssignableRoleId = assignablePermissionsForAssignableRoleId;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    public Set<UserRole> getUserRoles()
    {
        return this.userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles)
    {
        this.userRoles = userRoles;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roleByRoleId")
    public Set<AssignableRole> getAssignablePermissionsForRoleId()
    {
        return this.assignablePermissionsForRoleId;
    }

    public void setAssignablePermissionsForRoleId(
            Set<AssignableRole> assignablePermissionsForRoleId)
    {
        this.assignablePermissionsForRoleId = assignablePermissionsForRoleId;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    public Set<RolePermission> getRolePermissions()
    {
        return this.rolePermissions;
    }

    public void setRolePermissions(Set<RolePermission> rolePermissions)
    {
        this.rolePermissions = rolePermissions;
    }

    @Column(name = "NAME", length = 50)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
        final Role other = (Role) obj;
        if(id == null)
        {
            if(other.id != null)
                return false;
        }
        else if(!id.equals(other.id))
            return false;
        return true;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param code
     *            The code to set.
     */
    public void setBCode(String bcode)
    {
        this.bCode = bcode;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String code.
     */
    @Column(name = "BCODE", length = 50)
    public String getBCode()
    {
        return bCode;
    }

}