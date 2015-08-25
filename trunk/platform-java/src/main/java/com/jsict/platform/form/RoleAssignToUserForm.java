package com.jsict.platform.form;

import java.util.List;

import com.jsict.base.form.BaseForm;
import com.jsict.platform.domain.RoleDomain;
import com.jsict.platform.domain.UserAccountDomain;

public class RoleAssignToUserForm extends BaseForm
{
    private UserAccountDomain user;

    private List<RoleDomain> roleList;

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return UserAccountDomain user.
     */
    public UserAccountDomain getUser()
    {
        return user;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param user
     *            The user to set.
     */
    public void setUser(UserAccountDomain user)
    {
        this.user = user;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return List<RoleDomain> roleList.
     */
    public List<RoleDomain> getRoleList()
    {
        return roleList;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param roleList
     *            The roleList to set.
     */
    public void setRoleList(List<RoleDomain> roleList)
    {
        this.roleList = roleList;
    }
}
