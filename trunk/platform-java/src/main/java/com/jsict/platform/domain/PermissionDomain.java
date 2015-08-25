package com.jsict.platform.domain;

import java.util.ArrayList;
import java.util.List;

import com.jsict.base.BaseDomain;
import com.jsict.base.util.Text;

public class PermissionDomain extends BaseDomain
{
    private String code;

    private String description;

    private String type;

    private String url;

    private String itemId;


    private PermissionDomain parentPermission;

    private List<PermissionDomain> childPermissions;

    private String selected = "false";

    private String name;

    private String platform;


    public PermissionDomain()
    {
        childPermissions = new ArrayList<PermissionDomain>();
    }

    @Text(label = "权限编码")
    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
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

    @Text(label = "类型")
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Text(label = "URL")
    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    @Text(label = "元素ID")
    public String getItemId()
    {
        return itemId;
    }

    public void setItemId(String itemId)
    {
        this.itemId = itemId;
    }

    @Text(convert = false)
    public PermissionDomain getParentPermission()
    {
        return parentPermission;
    }

    public void setParentPermission(PermissionDomain parentPermission)
    {
        this.parentPermission = parentPermission;
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

    @Text(convert = false)
    public List<PermissionDomain> getChildPermissions()
    {
        return childPermissions;
    }

    public void setChildPermissions(List<PermissionDomain> childPermissions)
    {
        this.childPermissions = childPermissions;
    }

    @Text(label = "权限名称")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Text(label = "所属平台")
    public String getPlatform()
    {
        return platform;
    }

    public void setPlatform(String platform)
    {
        this.platform = platform;
    }

}
