package com.jsict.platform.domain;

import java.util.ArrayList;
import java.util.List;

import com.jsict.base.BaseDomain;
import com.jsict.base.annotation.Allow;
import com.jsict.base.util.Consts;
import com.jsict.base.util.Text;
import com.jsict.platform.constants.PermissionsConsts;

public class UserAccountDomain extends BaseDomain
{
    // Fields

    private CompanyDomain companyDomain;

    private String loginId;

    private String name;

    private String email;

    private String description;

    private SubsystemDomain subsystem;

    private String createdDate;

    private String status;

    private String loginIp;

    private String loginDate;

    private String password;

    private String wrongTime;

    private List<RoleDomain> assignedRoles;

    private boolean canEdit = true;

    private boolean canDo = true;

    private String editMode;

    private UserAccountDomain creator;

    public void setEditMode(String editMode)
    {
        this.editMode = editMode;
    }

    public String getEditMode()
    {
        return editMode;
    }

    public UserAccountDomain()
    {
        companyDomain = new CompanyDomain();
        assignedRoles = new ArrayList<RoleDomain>();
    }

    @Text(convert = false)
    public CompanyDomain getCompanyDomain()
    {
        return companyDomain;
    }

    public void setCompanyDomain(CompanyDomain companyDomain)
    {
        this.companyDomain = companyDomain;
    }

    @Text(label = "登陆ID")
    public String getLoginId()
    {
        return loginId;
    }

    public void setLoginId(String loginId)
    {
        this.loginId = loginId;
    }

    @Text(label = "用户名")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Text(label = "电子邮件")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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

    public void setCreatedDate(String createdDate)
    {
        this.createdDate = createdDate;
    }

    @Text(label = "状态")
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Text(label = "登陆IP")
    public String getLoginIp()
    {
        return loginIp;
    }

    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    @Text(label = "登陆日期", format = Consts.DATE_TIME_FORMAT)
    public String getLoginDate()
    {
        return loginDate;
    }

    public void setLoginDate(String loginDate)
    {
        this.loginDate = loginDate;
    }

    @Text(label = "状态")
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Text(label = "错误次数")
    public String getWrongTime()
    {
        return wrongTime;
    }

    public void setWrongTime(String wrongTime)
    {
        this.wrongTime = wrongTime;
    }

    @Text(convert = false)
    public List<RoleDomain> getAssignedRoles()
    {
        return assignedRoles;
    }

    public void setAssignedRoles(List<RoleDomain> assignedRoles)
    {
        this.assignedRoles = assignedRoles;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param subsystem
     *            The subsystem to set.
     */
    public void setSubsystem(SubsystemDomain subsystem)
    {
        this.subsystem = subsystem;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return SubsystemDomain subsystem.
     */
    public SubsystemDomain getSubsystem()
    {
        return subsystem;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param creator
     *            The creator to set.
     */
    public void setCreator(UserAccountDomain creator)
    {
        this.creator = creator;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return UserAccountDomain creator.
     */
    public UserAccountDomain getCreator()
    {
        return creator;
    }

    public void addRole(RoleDomain roleDomain)
    {
        this.assignedRoles.add(roleDomain);
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param canEdit
     *            The canEdit to set.
     */
    public void setCanEdit(boolean canEdit)
    {
        this.canEdit = canEdit;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return boolean canEdit.
     */
    @Allow(permissionCode = PermissionsConsts.PLF_EDITUSER)
    public boolean isCanEdit()
    {
        return canEdit;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param canDo
     *            The canDo to set.
     */
    public void setCanDo(boolean canDo)
    {
        this.canDo = canDo;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String canDo.
     */
    @Allow(permissionCode = PermissionsConsts.PLF_EDITUSER)
    public boolean isCanDo()
    {
        return canDo;
    }
}
