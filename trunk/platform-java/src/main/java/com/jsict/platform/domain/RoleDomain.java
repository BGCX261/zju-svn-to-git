package com.jsict.platform.domain;

import java.util.ArrayList;
import java.util.List;

import com.jsict.base.BaseDomain;
import com.jsict.base.util.Consts;
import com.jsict.base.util.Text;

public class RoleDomain extends BaseDomain
{
    // Fields
    private String description;

    private String createdDate;

    private String name;

    private List<PermissionDomain> selectedPermissions;

    private String selected = "false";

    private String bCode;

    private List<RoleDomain> assignableRoles;

    public RoleDomain()
    {
        selectedPermissions = new ArrayList<PermissionDomain>();
        setAssignableRoles(new ArrayList<RoleDomain>());
    }

    @Text(label = "描述")
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Text(label = "创建日期", format = Consts.DATE_TIME_FORMAT)
    public String getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(String createdDae)
    {
        this.createdDate = createdDae;
    }

    @Text(label = "角色名称")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Text(convert = false)
    public List<PermissionDomain> getSelectedPermissions()
    {
        return selectedPermissions;
    }

    public void setSelectedPermissions(
            List<PermissionDomain> selectedPermissions)
    {
        this.selectedPermissions = selectedPermissions;
    }

    @Text(convert = false)
    public String getSelected()
    {
        return selected;
    }

    public void setSelected(String selected)
    {
        this.selected = selected;
    }

    @Text
    public String getBCode()
    {
        return bCode;
    }

    public void setBCode(String code)
    {
        bCode = code;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @param assignableRoles The assignableRoles to set.
     */
    public void setAssignableRoles(List<RoleDomain> assignableRoles)
    {
        this.assignableRoles = assignableRoles;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @return List<RoleDomain> assignableRoles.
     */
    public List<RoleDomain> getAssignableRoles()
    {
        return assignableRoles;
    }

}
