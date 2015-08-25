package com.jsict.base.security;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.GrantedAuthority;

@SuppressWarnings("serial")
public class User extends org.springframework.security.userdetails.User
{
    private Serializable userId;

    private Serializable companyId;

    private String loginIp;

    private Date loginDate;

    private Set<String> permissions;

    private Set<String> roles;

    private Map<String, Boolean> platformPermissions = new HashMap<String, Boolean>();

    private String companyName;

    private String userDisplayName;

    public User(Serializable userId, Serializable companyId, String username,
            String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            GrantedAuthority[] authorities, String[] roles, String loginIp,
            Date loginDate) throws IllegalArgumentException
    {
        super(username, password, enabled, accountNonExpired,
            credentialsNonExpired, accountNonLocked, authorities);

        setUserId(userId);
        setCompanyId(companyId);
        setLoginDate(loginDate);
        setLoginIp(loginIp);

        permissions = new HashSet<String>();

        for (GrantedAuthority ga : this.getAuthorities())
        {
            permissions.add(ga.getAuthority());
        }

        this.roles = new HashSet<String>(Arrays.asList(roles));
    }

    /**
     * <p>
     * Description:用户是否至少有子系统的一项权限/p>
     * 
     * @param platform
     * @return
     * @author: lgq
     * @update: [updatedate] [changer][change description]
     */
    public boolean hasPlatformPermission(String platformId)
    {
        Boolean rvt = (Boolean) platformPermissions.get(platformId);
        if(rvt == null)
        {
            for (String permission : permissions)
            {
                if(permission.startsWith("/" + platformId))
                {
                    rvt = true;
                    platformPermissions.put(platformId, rvt);
                    break;
                }
            }
            if(rvt == null)
                rvt = false;
            platformPermissions.put(platformId, rvt);
        }
        return rvt;
    }

    public Serializable getUserId()
    {
        return userId;
    }

    public void setUserId(Serializable userId)
    {
        this.userId = userId;
    }

    public Serializable getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(Serializable companyId)
    {
        this.companyId = companyId;
    }

    public String getLoginIp()
    {
        return loginIp;
    }

    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    public Date getLoginDate()
    {
        return loginDate;
    }

    public void setLoginDate(Date loginDate)
    {
        this.loginDate = loginDate;
    }

    public boolean hasPermission(String permissionCode)
    {
        return this.permissions.contains(permissionCode);

    }

    public boolean hasRole(String role)
    {
        return this.roles.contains(role);
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param companyName
     *            The companyName to set.
     */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String companyName.
     */
    public String getCompanyName()
    {
        return companyName;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @param userDisplayName The userDisplayName to set.
     */
    public void setUserDisplayName(String userDisplayName)
    {
        this.userDisplayName = userDisplayName;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @return String userDisplayName.
     */
    public String getUserDisplayName()
    {
        return userDisplayName;
    }

}
