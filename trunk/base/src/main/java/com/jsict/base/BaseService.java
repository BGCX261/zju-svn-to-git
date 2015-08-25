package com.jsict.base;

import java.io.Serializable;

import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

import com.jsict.base.security.User;

public class BaseService implements IBaseService
{
    public Serializable getUserId()
    {
        User user = getUser();

        return user == null ? null : user.getUserId();
    }

    public User getUser()
    {
        SecurityContext context = SecurityContextHolder.getContext();
        return (User) context.getAuthentication().getPrincipal();

    }

    public boolean hasPermission(String permissionCode)
    {
        return getUser().hasPermission(permissionCode);
    }

    public boolean hasRole(String roleCode)
    {
        return getUser().hasRole(roleCode);
    }

}
