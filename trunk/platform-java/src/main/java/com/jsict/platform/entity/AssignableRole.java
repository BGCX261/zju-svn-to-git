package com.jsict.platform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AssignablePermission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ASSIGNABLE_ROLE")
public class AssignableRole implements java.io.Serializable
{

    // Fields    

    private Long id;

    private Role roleByRoleId;

    private Role roleByAssignableRoleId;

    private String rights;

    private String status;

    // Constructors

    /** default constructor */
    public AssignableRole()
    {
    }

    /** full constructor */
    public AssignableRole(Role roleByRoleId, Role roleByAssignableRoleId,
            String rights, String status)
    {
        this.roleByRoleId = roleByRoleId;
        this.roleByAssignableRoleId = roleByAssignableRoleId;
        this.rights = rights;
        this.status = status;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    public Role getRoleByRoleId()
    {
        return this.roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId)
    {
        this.roleByRoleId = roleByRoleId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSIGNABLE_ROLE_ID")
    public Role getRoleByAssignableRoleId()
    {
        return this.roleByAssignableRoleId;
    }

    public void setRoleByAssignableRoleId(Role roleByAssignableRoleId)
    {
        this.roleByAssignableRoleId = roleByAssignableRoleId;
    }

    @Column(name = "RIGHTS", length = 6)
    public String getRights()
    {
        return this.rights;
    }

    public void setRights(String rights)
    {
        this.rights = rights;
    }

    @Column(name = "STATUS", length = 6)
    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

}